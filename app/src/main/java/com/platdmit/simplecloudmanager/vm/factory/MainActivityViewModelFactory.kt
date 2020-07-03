package com.platdmit.simplecloudmanager.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.platdmit.data.helpers.ActualApiKeyService
import com.platdmit.domain.helpers.ActualApiKeyServiceManager

class MainActivityViewModelFactory(private val mActualApiKeyServiceManager: ActualApiKeyServiceManager) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return try {
            modelClass.getConstructor(ActualApiKeyServiceManager::class.java).newInstance(mActualApiKeyServiceManager)
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