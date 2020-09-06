package com.platdmit.simplecloudmanager.screens.domain

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.platdmit.domain.models.Domain
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.base.extensions.setLoaderStatus
import com.platdmit.simplecloudmanager.databinding.FragmentDomainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DomainFragment : Fragment(R.layout.fragment_domain) {
    private val domainViewModel: DomainViewModel by viewModels()
    private val domainViewBinding: FragmentDomainBinding by viewBinding()
    private val domainRecordListAdapter = DomainRecordListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        domainViewBinding.domainRecordList.layoutManager = LinearLayoutManager(context)
        domainViewModel.domainStateLiveData.observe(viewLifecycleOwner, { stateHandler(it) })
    }

    private fun stateHandler(domainState: DomainState){
        when(domainState){
            is DomainState.Loading -> {
                setLoaderStatus(true)
            }
            is DomainState.Success -> {
                initData(domainState.domain)
                setLoaderStatus(false)
            }
            is DomainState.Empty -> {
                setLoaderStatus(false)
            }
            is DomainState.Error -> {}
        }
    }

    private fun setStateInstance(stateInstance: DomainViewModel.StateIntent){
        domainViewModel.setStateIntent(stateInstance)
    }

    private fun initData(domain: Domain) {
        domainViewBinding.domainName.text = domain.name
        domainViewBinding.domainType.text = domain.type
        domain.domainRecords?.let { domainRecordListAdapter.setContentData(it) }
        if (domainViewBinding.domainRecordList.adapter == null) {
            domainViewBinding.domainRecordList.adapter = domainRecordListAdapter
        } else {
            domainRecordListAdapter.notifyDataSetChanged()
        }
    }
}