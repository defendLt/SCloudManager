package com.platdmit.simplecloudmanager.app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.rxbinding4.widget.RxTextView;
import com.platdmit.simplecloudmanager.R;
import com.platdmit.simplecloudmanager.app.helpers.UiVisibilityStatus;
import com.platdmit.simplecloudmanager.app.vm.LoginViewModel;
import com.platdmit.simplecloudmanager.app.vm.factory.LoginViewModelFactory;
import com.platdmit.simplecloudmanager.data.api.implement.ApiAccountRepoImp;
import com.platdmit.simplecloudmanager.domain.SCMApp;
import com.platdmit.simplecloudmanager.domain.converters.implement.AccountConvertImp;
import com.platdmit.simplecloudmanager.domain.repo.AccountRepo;
import com.platdmit.simplecloudmanager.domain.repo.implement.AccountRepoImp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

import static androidx.navigation.Navigation.findNavController;

public class LoginFragment extends Fragment {
    private static final String TAG = LoginFragment.class.getSimpleName();
    private LoginViewModel mLoginViewModel;
    private InputMethodManager mInputMethodManager;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private ConstraintLayout mLoginFormLayout;
    private ConstraintLayout mPinFormLayout;
    private ConstraintLayout mLoaderLayout;

    private EditText mAccountLogin;
    private EditText mAccountPass;
    private EditText mAccountPinCode;
    private Button mAccountSubmit;
    private Button mOnDemoAccount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Hide Bottom menu and Toolbar
        setUiVisibleStatus(false);

        if(savedInstanceState != null){
            mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        } else {
            mLoginViewModel = new ViewModelProvider(this,
                    new LoginViewModelFactory<AccountRepo>(
                            new AccountRepoImp(
                                    new ApiAccountRepoImp(),
                                    SCMApp.getDb(),
                                    new AccountConvertImp()
                            ), AccountRepo.class, SCMApp.getActualApiKeyService()
                    )).get(LoginViewModel.class);
        }

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        mLoginFormLayout = v.findViewById(R.id.login_layout);
        mPinFormLayout = v.findViewById(R.id.pin_layout);
        mLoaderLayout = v.findViewById(R.id.loader_layout);

        mAccountLogin = v.findViewById(R.id.user_login);
        mAccountPass = v.findViewById(R.id.user_pass);
        mAccountPinCode = v.findViewById(R.id.user_pin_code);

        mOnDemoAccount = v.findViewById(R.id.form_demo_submit);
        mOnDemoAccount.setOnClickListener(view -> {
            mLoginViewModel.onDemoAccount();
        });

        mAccountSubmit = v.findViewById(R.id.form_submit);
        mAccountSubmit.setOnClickListener(view -> {
            view.setEnabled(false);
            mLoginViewModel.addNewAccount(mAccountLogin.getText().toString(), mAccountPass.getText().toString());
        });

        mLoginViewModel.getAuthStatus().observe(getViewLifecycleOwner(), this::authStatusHandler);
        mLoginViewModel.getRegStatus().observe(getViewLifecycleOwner(), this::regStatusHandler);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
        setUiVisibleStatus(true);
    }

    private void regStatusHandler(LoginViewModel.LoginFormStatus status){
        switch (status){
            case AUTH_INVALID:
                showTextAlert(R.string.login_submit_not_found);
                break;
            case NEED_SET_PIN:
                showTextAlert(R.string.login_submit_correct);
                mLoginFormLayout.setVisibility(View.GONE);
                pinFormInit(true);
                break;
        }
    }

    private void authStatusHandler(LoginViewModel.LoginFormStatus status){
        switch (status){
            case YES_ACTIVE_USER:
                mLoaderLayout.setVisibility(View.GONE);
                pinFormInit(false);
                break;
            case NOT_ACTIVE_USER:
                mLoaderLayout.setVisibility(View.GONE);
                loginFormInit();
                break;
            case PIN_INVALID:
                authFall();
                break;
            case ON_DEMO:
                authDemo();
                break;
            case SUCCESS:
                authSuccess();
                break;
        }
    }

    private void pinFormInit(boolean isNew){
        if(isNew){
            TextView formTitle = getView().findViewById(R.id.pin_layout_title);
            formTitle.setText(R.string.pin_layout_title_new);
            mCompositeDisposable.add(
                    RxTextView.textChanges(mAccountPinCode)
                            .filter(pinText -> pinText.toString().length() == 4)
                            .map(CharSequence::toString)
                            .subscribe(mLoginViewModel::addNewAccountPin)
            );
        } else {
            mCompositeDisposable.add(
                    RxTextView.textChanges(mAccountPinCode)
                            .filter(pinText -> pinText.toString().length() == 4)
                            .map(CharSequence::toString)
                            .subscribe(mLoginViewModel::checkAccountPin)
            );
        }
        mPinFormLayout.setVisibility(View.VISIBLE);
        mAccountPinCode.requestFocus();
        mInputMethodManager.showSoftInput(mAccountPinCode, InputMethodManager.SHOW_IMPLICIT);
    }

    private void loginFormInit(){
        mLoginFormLayout.setVisibility(View.VISIBLE);

        mCompositeDisposable.add(Observable.combineLatest(
                RxTextView.textChanges(mAccountLogin),
                RxTextView.textChanges(mAccountPass),
                (login, pass) -> login.length() > 0 && pass.length() > 0)
                .subscribe(mAccountSubmit::setEnabled)
        );
    }

    private void authSuccess(){
        try {
            mInputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            findNavController(getView()).popBackStack();
            findNavController(getView()).navigate(R.id.serverListFragment);
        } catch (NullPointerException ignored) {
        }
    }

    private void authFall(){
        showTextAlert(R.string.login_pin_incorrect);
        showVibratorAlert();
        mAccountPinCode.getText().clear();
    }

    private void authDemo() {
        try {
            mInputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
            this.getViewModelStore().clear();
            findNavController(getView()).navigate(R.id.serverListFragment);
            setUiVisibleStatus(true);
        } catch (NullPointerException ignored) {
        }
    }

    private void setUiVisibleStatus(boolean status){
        try {
            ((UiVisibilityStatus)getActivity()).setVisibilityToolbar(status);
            ((UiVisibilityStatus)getActivity()).setVisibilityNavigation(status);
        } catch (NullPointerException ignored){
        }
    }

    private void showTextAlert(int stringId){
        Snackbar.make(getView(), stringId, Snackbar.LENGTH_SHORT).show();
    }

    private void showVibratorAlert(){
        try {
            Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
            if (vibrator.hasVibrator()) {
                vibrator.vibrate(200);
            }
        } catch (NullPointerException ignored){
        }
    }
}
