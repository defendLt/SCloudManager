package com.platdmit.simplecloudmanager.states

sealed class LoginState {
    object ActiveUserYes: LoginState()
    object ActiveUserNo: LoginState()
    object UserNeedPin: LoginState()
    object PinInvalid : LoginState()
    object AuthInvalid : LoginState()
    object OnDemo : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
}