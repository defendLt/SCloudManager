package com.platdmit.simplecloudmanager.screens.login

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.jakewharton.rxbinding4.widget.textChanges
import com.platdmit.domain.BuildConfig
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.base.extensions.setLoaderStatus
import com.platdmit.simplecloudmanager.base.extensions.showResultMessage
import com.platdmit.simplecloudmanager.base.extensions.toComposite
import com.platdmit.simplecloudmanager.databinding.FragmentLoginBinding
import com.platdmit.simplecloudmanager.utilities.ErrorMassageHandler
import com.platdmit.simplecloudmanager.utilities.UiVisibilityStatus
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login){
    private val loginViewModel: LoginViewModel by viewModels()
    private val loginViewBinding: FragmentLoginBinding by viewBinding()
    private val inputMethodManager: InputMethodManager? = context?.getSystemService()
    private val compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var errorMassageHandler : ErrorMassageHandler

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //Hide Bottom menu and Toolbar
        setUiVisibleStatus(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ViewModel observe
        loginViewModel.loginStateLiveData.observe(viewLifecycleOwner, ::stateHandler)

        loginViewBinding.run {
            //On check auth
            formSubmit.setOnClickListener {
                it.isEnabled = false
                setStateIntent(
                        LoginViewModel.StateIntent.NewAccount(
                                userLogin.text.toString(),
                                userPass.text.toString()
                        )
                )
            }
            //On demo mode
            if(BuildConfig.DEBUG){
                formDemoSubmit.isVisible = true
                formDemoSubmit.setOnClickListener {
                    setStateIntent(
                            LoginViewModel.StateIntent.OnDemoMode
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        setUiVisibleStatus(true)
    }

    override fun onPause() {
        super.onPause()
        setStateIntent(LoginViewModel.StateIntent.ResetScreen)
    }

    private fun stateHandler(loginState: LoginState){
        when(loginState){
            is LoginState.ActiveUserYes -> {
                setLoaderStatus(false)
                pinFormInit(false)
            }
            is LoginState.ActiveUserNo -> {
                setLoaderStatus(false)
                loginFormInit()
            }
            is LoginState.UserNeedPin -> {
                showResultMessage(R.string.login_submit_correct)
                loginViewBinding.loginLayout.isVisible = false
                pinFormInit(true)
            }
            is LoginState.PinInvalid -> {
                authFall()
            }
            is LoginState.AuthInvalid -> {
                showResultMessage(R.string.login_submit_not_found)
                showVibratorAlert()
            }
            is LoginState.OnDemo -> {
                authDemo()
            }
            is LoginState.Success -> {
                authSuccess()
            }
            is LoginState.Error -> {
                showResultMessage(errorMassageHandler.getMessageId(loginState.errorType))
            }
            is LoginState.Loading -> {
                setLoaderStatus(true)
            }
        }
    }

    private fun setStateIntent(stateInstance: LoginViewModel.StateIntent){
        loginViewModel.setStateIntent(stateInstance)
    }

    private fun pinFormInit(isNew: Boolean) {
        loginViewBinding.run {
            if (isNew) {
                pinLayoutTitle.setText(R.string.pin_layout_title_new)
                userPinCode.textChanges()
                        .filter { it.toString().length == 4 }
                        .map { it.toString() }
                        .subscribe {
                            setStateIntent(
                                    LoginViewModel.StateIntent.NewAccountPin(it)
                            )
                        }.toComposite(compositeDisposable)
            } else {
                userPinCode.textChanges()
                        .filter { it.toString().length == 4 }
                        .map { it.toString() }
                        .subscribe {
                            setStateIntent(
                                    LoginViewModel.StateIntent.CheckAccountPin(it)
                            )
                        }.toComposite(compositeDisposable)
            }
            pinLayout.isVisible = true
            userPinCode.requestFocus()
            inputMethodManager?.showSoftInput(userPinCode, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun loginFormInit() {
        loginViewBinding.run {
            loginLayout.isVisible = true
            Observable.combineLatest(
                    userLogin.textChanges(),
                    userPass.textChanges(),
                    { login: CharSequence, pass: CharSequence -> login.isNotEmpty() && pass.isNotEmpty() })
                    .subscribe { formSubmit.isEnabled = it }
                    .toComposite(compositeDisposable)
        }
    }

    private fun authSuccess() {
        inputMethodManager?.hideSoftInputFromWindow(requireView().windowToken, 0)
        view?.findNavController()?.navigate(R.id.action_loginFragment_to_serverListFragment)
    }

    private fun authFall() {
        showResultMessage(R.string.login_pin_incorrect)
        showVibratorAlert()
        loginViewBinding.userPinCode.text.clear()
    }

    private fun authDemo() {
        inputMethodManager?.hideSoftInputFromWindow(requireView().windowToken, 0)
        view?.findNavController()?.navigate(R.id.serverListFragment)
        setUiVisibleStatus(true)
    }

    private fun setUiVisibleStatus(status: Boolean) {
        (activity as? UiVisibilityStatus)?.run {
            setVisibilityToolbar(status)
            setVisibilityNavigation(status)
        }
    }

    private fun showVibratorAlert() {
        val vibrator : Vibrator? = context?.getSystemService()
        vibrator?.run {
            if (hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrate(200)
                }
            }
        }
    }
}