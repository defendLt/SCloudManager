package com.platdmit.simplecloudmanager.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.helpers.UiVisibilityStatus
import com.platdmit.simplecloudmanager.vm.LoginViewModel
import com.platdmit.simplecloudmanager.vm.LoginViewModel.LoginFormStatus
import com.platdmit.simplecloudmanager.vm.factory.LoginViewModelFactory
import com.platdmit.data.api.implement.ApiAccountRepoImp
import com.platdmit.simplecloudmanager.SCMApp
import com.platdmit.domain.converters.implement.AccountConvertImp
import com.platdmit.domain.repo.AccountRepo
import com.platdmit.domain.repo.implement.AccountRepoImp
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.functions.BiFunction
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    private lateinit var mLoginViewModel: LoginViewModel
    private lateinit var mInputMethodManager: InputMethodManager
    private val mCompositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Hide Bottom menu and Toolbar
        setUiVisibleStatus(false)
        mLoginViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(LoginViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    LoginViewModelFactory(
                            AccountRepoImp(
                                    ApiAccountRepoImp(),
                                    SCMApp.db,
                                    AccountConvertImp()
                            ), AccountRepo::class.java, SCMApp.actualApiKeyService
                    )).get(LoginViewModel::class.java)
        }
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mInputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        mLoginViewModel.authStatus.observe(viewLifecycleOwner, Observer { authStatusHandler(it) })
        mLoginViewModel.regStatus.observe(viewLifecycleOwner, Observer { regStatusHandler(it) })

        //On demo mode
        form_demo_submit.setOnClickListener { mLoginViewModel.onDemoAccount() }

        //On check auth
        form_submit.setOnClickListener {
            it.isEnabled = false
            mLoginViewModel.addNewAccount(user_login.text.toString(), user_pass.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable.clear()
        setUiVisibleStatus(true)
    }

    private fun regStatusHandler(status: LoginFormStatus) {
        when (status) {
            LoginFormStatus.AUTH_INVALID -> {
                showTextAlert(R.string.login_submit_not_found)
                showVibratorAlert()
            }
            LoginFormStatus.NEED_SET_PIN -> {
                showTextAlert(R.string.login_submit_correct)
                login_layout.visibility = View.GONE
                pinFormInit(true)
            }
        }
    }

    private fun authStatusHandler(status: LoginFormStatus) {
        when (status) {
            LoginFormStatus.YES_ACTIVE_USER -> {
                loader_layout.visibility = View.GONE
                pinFormInit(false)
            }
            LoginFormStatus.NOT_ACTIVE_USER -> {
                loader_layout.visibility = View.GONE
                loginFormInit()
            }
            LoginFormStatus.PIN_INVALID -> authFall()
            LoginFormStatus.ON_DEMO -> authDemo()
            LoginFormStatus.SUCCESS -> authSuccess()
        }
    }

    private fun pinFormInit(isNew: Boolean) {
        if (isNew) {
            pin_layout_title.setText(R.string.pin_layout_title_new)
            mCompositeDisposable.add(
                    user_pin_code.textChanges()
                            .filter { it.toString().length == 4 }
                            .map { it.toString() }
                            .subscribe { mLoginViewModel.addNewAccountPin(it) }
            )
        } else {
            mCompositeDisposable.add(
                    user_pin_code.textChanges()
                            .filter { it.toString().length == 4 }
                            .map { it.toString() }
                            .subscribe { mLoginViewModel.checkAccountPin(it) }
            )
        }
        pin_layout.visibility = View.VISIBLE
        user_pin_code.requestFocus()
        mInputMethodManager.showSoftInput(user_pin_code, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun loginFormInit() {
        login_layout.visibility = View.VISIBLE
        mCompositeDisposable.add(Observable.combineLatest(
                user_login.textChanges(),
                user_pass.textChanges(),
                BiFunction { login: CharSequence, pass: CharSequence -> login.isNotEmpty() && pass.isNotEmpty() })
                .subscribe { form_submit.isEnabled = it }
        )
    }

    private fun authSuccess() {
        try {
            mInputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
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
            mInputMethodManager.hideSoftInputFromWindow(requireView().windowToken, 0)
            this.viewModelStore.clear()
            Navigation.findNavController(requireView()).navigate(R.id.serverListFragment)
            setUiVisibleStatus(true)
        } catch (ignored: NullPointerException) {
        }
    }

    private fun setUiVisibleStatus(status: Boolean) {
        try {
            (activity as UiVisibilityStatus?)!!.setVisibilityToolbar(status)
            (activity as UiVisibilityStatus?)!!.setVisibilityNavigation(status)
        } catch (ignored: NullPointerException) {
        }
    }

    private fun showTextAlert(stringId: Int) {
        Snackbar.make(requireView(), stringId, Snackbar.LENGTH_SHORT).show()
    }

    private fun showVibratorAlert() {
        try {
            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (vibrator.hasVibrator()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(200)
                }
            }
        } catch (ignored: NullPointerException) {
        }
    }

    companion object {
        private val TAG = LoginFragment::class.java.simpleName
    }
}