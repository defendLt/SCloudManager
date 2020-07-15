package com.platdmit.simplecloudmanager.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
@Deprecated("Use Hilt")
class ListElementsViewModelFactory<Repo>(private val repo: Repo, private val repoInterface: Class<Repo>) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return try {
            modelClass.getConstructor(repoInterface).newInstance(repo)
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