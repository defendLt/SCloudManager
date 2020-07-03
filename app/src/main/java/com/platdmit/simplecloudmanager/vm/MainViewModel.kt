package com.platdmit.simplecloudmanager.vm

import androidx.lifecycle.ViewModel
import com.platdmit.domain.helpers.ActualApiKeyServiceManager

class MainViewModel(
        private val mActualApiKeyService: ActualApiKeyServiceManager
) : ViewModel() {
    companion object {
        private val TAG = LoginViewModel::class.java.simpleName
    }

    override fun onCleared() {
        super.onCleared()
        mActualApiKeyService.stopAutoUpdate()
    }
}