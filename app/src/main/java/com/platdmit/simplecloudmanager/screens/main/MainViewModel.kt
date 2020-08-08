package com.platdmit.simplecloudmanager.screens.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.platdmit.base_utils.apikey_service.domain.ActualApiKeyServiceManager

class MainViewModel
@ViewModelInject
constructor(
        private val actualApiKeyServiceManager: ActualApiKeyServiceManager,
        @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    override fun onCleared() {
        super.onCleared()
        actualApiKeyServiceManager.stopAutoUpdate()
    }
}