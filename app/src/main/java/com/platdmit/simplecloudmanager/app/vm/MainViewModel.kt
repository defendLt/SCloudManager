package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.ViewModel
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService

class MainViewModel(
        private val mActualApiKeyService: ActualApiKeyService
) : ViewModel() {
    companion object {
        private val TAG = LoginViewModel::class.java.simpleName
    }

    override fun onCleared() {
        super.onCleared()
        mActualApiKeyService.stopAutoUpdate()
    }
}