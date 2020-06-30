package com.platdmit.simplecloudmanager.app.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.platdmit.simplecloudmanager.domain.helpers.ActualApiKeyService

class LoginViewModelFactory<Repo>(private val repo: Repo, private val repoInterface: Class<Repo>, private val actualApiKeyService: ActualApiKeyService) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return try {
            modelClass.getConstructor(repoInterface, ActualApiKeyService::class.java).newInstance(repo, actualApiKeyService)
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