package com.platdmit.simplecloudmanager.utilities

import com.platdmit.domain.enums.ErrorType
import com.platdmit.simplecloudmanager.R
import javax.inject.Inject

class ErrorMessageHandlerImp
@Inject
constructor() : ErrorMassageHandler{
    override fun getMessageId(errorType: ErrorType): Int {
        return when(errorType){
            ErrorType.EMPTY_RESULT -> R.string.error_empty
            ErrorType.FALL_CONNECT -> R.string.error_fall_connect
            ErrorType.FALL_AUTH -> R.string.error_fall_auth
            ErrorType.FALL_REQUEST -> R.string.error_fall_request
        }
    }
}