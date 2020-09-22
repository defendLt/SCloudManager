package com.platdmit.simplecloudmanager.screens.domains

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.platdmit.domain.models.Domain
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.base.extensions.setLoaderStatus
import com.platdmit.simplecloudmanager.base.extensions.showResultMessage
import com.platdmit.simplecloudmanager.databinding.FragmentDomainsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DomainListFragment : Fragment(R.layout.fragment_domains_list){
    private val domainListViewModel: DomainListViewModel by viewModels()
    private val domainListViewBinding: FragmentDomainsListBinding by viewBinding()
    private val domainListAdapter: DomainListAdapter = DomainListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        domainListViewBinding.run {
            fragmentsList.layoutManager = LinearLayoutManager(context)

            updateSwipe.setOnRefreshListener{
                setStateInstance(DomainListViewModel.StateIntent.RefreshResult)
            }
        }

        domainListViewModel.domainsStateLiveData.observe(viewLifecycleOwner, ::stateHandler)
    }

    private fun stateHandler(domainListState: DomainListState){
        when(domainListState){
            is DomainListState.Loading -> {
                setLoaderStatus(true)
            }
            is DomainListState.Success -> {
                updateAdapterData(domainListState.domains)
                setLoaderStatus(false)
            }
            is DomainListState.Empty -> {}
            is DomainListState.Error -> {
                setLoaderStatus(false)
            }
        }
    }

    private fun setStateInstance(stateInstance: DomainListViewModel.StateIntent){
        domainListViewModel.setStateIntent(stateInstance)
    }

    private fun updateAdapterData(domains: List<Domain>) {
        domainListViewBinding.run {
            updateSwipe.isRefreshing = false

            if (fragmentsList.adapter == null) {
                fragmentsList.adapter = domainListAdapter
            }
        }
        domainListAdapter.setContentData(domains)
    }
}