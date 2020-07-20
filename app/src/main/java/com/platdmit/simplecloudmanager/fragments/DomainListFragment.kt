package com.platdmit.simplecloudmanager.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.platdmit.domain.models.Domain
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.adapters.DomainListAdapter
import com.platdmit.simplecloudmanager.states.DomainListState
import com.platdmit.simplecloudmanager.vm.DomainListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_domains_list.*

@AndroidEntryPoint
class DomainListFragment : Fragment(R.layout.fragment_domains_list) {
    private val domainListViewModel: DomainListViewModel by viewModels()
    private val domainListAdapter: DomainListAdapter = DomainListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragments_list.layoutManager = LinearLayoutManager(context)
        update_swipe.setOnRefreshListener{
            setStateInstance(DomainListViewModel.StateIntent.RefreshResult)
        }

        domainListViewModel.domainsStateLiveData.observe(viewLifecycleOwner, Observer {stateHandler(it)})
        domainListViewModel.messageLiveData.observe(viewLifecycleOwner, Observer {showResultMassage(it)})
    }

    private fun stateHandler(domainListState: DomainListState){
        when(domainListState){
            is DomainListState.Loading -> {}
            is DomainListState.Success -> {
                updateAdapterData(domainListState.domains)
            }
            is DomainListState.Empty -> {}
            is DomainListState.Error -> {}
        }
    }

    private fun setStateInstance(stateInstance: DomainListViewModel.StateIntent){
        domainListViewModel.setStateIntent(stateInstance)
    }

    private fun showResultMassage(massage: String) {
        Snackbar.make(requireView(), massage, Snackbar.LENGTH_SHORT).show()
    }

    private fun updateAdapterData(domains: List<Domain>) {
        domainListAdapter.setContentData(domains)
        update_swipe.isRefreshing = false

        if (fragments_list.adapter == null) {
            fragments_list.adapter = domainListAdapter
        } else {
            domainListAdapter.notifyDataSetChanged()
        }
    }
}