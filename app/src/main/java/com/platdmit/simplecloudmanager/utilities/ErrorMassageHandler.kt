package com.platdmit.simplecloudmanager.utilities

import com.platdmit.domain.enums.ErrorType

interface ErrorMassageHandler {
    fun getMessageId(errorType: ErrorType) : Int
}