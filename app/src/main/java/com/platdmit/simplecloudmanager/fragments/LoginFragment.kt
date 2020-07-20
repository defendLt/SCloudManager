package com.platdmit.simplecloudmanager.fragments

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.helpers.UiVisibilityStatus
import com.platdmit.simplecloudmanager.states.LoginState
import com.platdmit.simplecloudmanager.vm.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private val loginViewModel: LoginViewModel by viewModels()
    private val inputMethodManager: InputMethodManager? = context?.getSystemService()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Hide Bottom menu and Toolbar
        setUiVisibleStatus(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.loginStateLiveData.observe(viewLifecycleOwner, Observer { stateHandler(it) })

        //On demo mode
        form_demo_submit.setOnClickListener {
            setStateInstance(
                    LoginViewModel.StateInstance.OnDemoMode
            )
        }

        //On check auth
        form_submit.setOnClickListener {
            it.isEnabled = false
            setStateInstance(
                    LoginViewModel.StateInstance.NewAccount(user_login.text.toString(), user_pass.text.toString())
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        setUiVisibleStatus(true)
    }

    private fun stateHandler(loginState: LoginState){
        when(loginState){
            is LoginState.ActiveUserYes -> {
                loader_layout.visibility = View.GONE
                pinFormInit(false)
            }
            is LoginState.ActiveUserNo -> {
                loader_layout.visibility = View.GONE
                loginFormInit()
            }
            is LoginState.UserNeedPin -> {
                showTextAlert(R.string.login_submit_correct)
                login_layout.visibility = View.GONE
                pinFormInit(true)
            }
            is LoginState.PinInvalid -> {
                authFall()
            }
            is LoginState.AuthInvalid -> {
                showTextAlert(R.string.login_submit_not_found)
                showVibratorAlert()
            }
            is LoginState.OnDemo -> {
                authDemo()
            }
            is LoginState.Success -> {
                authSuccess()
            }
            is LoginState.Loading -> {
                authDemo()
            }
        }
    }

    private fun setStateInstance(stateInstance: LoginViewModel.StateInstance){
        loginViewModel.setStateInstance(stateInstance)
    }

    private fun pinFormInit(isNew: Boolean) {
        if (isNew) {
            pin_layout_title.setText(R.string.pin_layout_title_new)
            compositeDisposable.add(
                    user_pin_code.textChanges()
                            .filter { it.toString().length == 4 }
                            .map { it.toString() }
                            .subscribe {
                                setStateInstance(
                                        LoginViewModel.StateInstance.NewAccountPin(it)
                                )
                            }
            )
        } else {
            compositeDisposable.add(
                    user_pin_code.textChanges()
                            .filter { it.toString().length == 4 }
                            .map { it.toString() }
                            .subscribe {
                                setStateInstance(
                                        LoginViewModel.StateInstance.CheckAccountPin(it)
                                )
                            }
            )
        }
        pin_layout.visibility = View.VISIBLE
        user_pin_code.requestFocus()
        inputMethodManager?.showSoftInput(user_pin_code, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun loginFormInit() {
        login_layout.visibility = View.VISIBLE
        compositeDisposable.add(Observable.combineLatest(
                user_login.textChanges(),
                user_pass.textChanges(),
                BiFunction { login: CharSequence, pass: CharSequence -> login.isNotEmpty() && pass.isNotEmpty() })
                .subscribe { form_submit.isEnabled = it }
        )
    }

    private fun authSuccess() {
        try {
            inputMethodManager?.hideSoftInputFromWindow(requireView().windowToken, 0)
            Navigation.findNavController(requireView()).popBackStack()
            Navigation.findNavController(requireView()).navigate(R.id.serverListFragment)
        } catch (ignored: NullPointerException) {
        }
    }

    private fun authFall() {
        showTextAlert(R.string.login_pin_incorrect)
        showVibratorAlert()
        user_pin_code.text.clear()
    }

    private fun authDemo() {
        try {
            inputMethodManager?.hideSoftInputFromWindow(requireView().windowToken, 0)
            this.viewModelStore.clear()
            Navigation.findNavController(requireView()).navigate(R.id.serverListFragment)
            setUiVisibleStatus(true)
        } catch (ignored: NullPointerException) {
        }
    }

    private fun setUiVisibleStatus(status: Boolean) {
        try {
            (activity as? UiVisibilityStatus)?.setVisibilityToolbar(status)
            (activity as? UiVisibilityStatus)?.setVisibilityNavigation(status)
        } catch (ignored: NullPointerException) {
        }
    }

    private fun showTextAlert(stringId: Int) {
        Snackbar.make(requireView(), stringId, Snackbar.LENGTH_SHORT).show()
    }

    private fun showVibratorAlert() {
        try {
            val vibrator : Vibrator? = context?.getSystemService()
            vibrator?.let {
                if (vibrator.hasVibrator()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        vibrator.vibrate(200)
                    }
                }
            }
        } catch (ignored: NullPointerException) {
        }
    }
}