package com.platdmit.simplecloudmanager.app.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService

class MainActivityViewModelFactory(private val mActualApiKeyService: ActualApiKeyService) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return try {
            modelClass.getConstructor(ActualApiKeyService::class.java).newInstance(mActualApiKeyService)
        } catch (e: Exception) {
            try {
                modelClass.newInstance()
            } catch (g: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", g)
            } catch (g: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", g)
            }
        }
    }

}