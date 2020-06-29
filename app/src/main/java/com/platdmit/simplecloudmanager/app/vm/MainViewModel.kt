package com.platdmit.simplecloudmanager.app.vm

import androidx.lifecycle.ViewModel
import com.platdmit.simplecloudmanager.app.vm.LoginViewModel
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService

class MainViewModel(private val mActualApiKeyService: ActualApiKeyService) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        mActualApiKeyService.stopAutoUpdate()
    }

    companion object {
        private val TAG = LoginViewModel::class.java.simpleName
    }

}