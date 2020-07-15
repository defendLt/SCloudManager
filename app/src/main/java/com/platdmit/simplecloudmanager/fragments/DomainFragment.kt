package com.platdmit.simplecloudmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.platdmit.domain.models.Domain
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.adapters.DomainRecordListAdapter
import com.platdmit.simplecloudmanager.vm.DomainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_domain.*

@AndroidEntryPoint
class DomainFragment : Fragment(R.layout.fragment_domain) {
    private val domainViewModel: DomainViewModel by viewModels()
    private val domainRecordListAdapter = DomainRecordListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            domainViewModel.setActiveId(requireArguments().getLong("ELEMENT_ID"))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        domain_record_list.layoutManager = LinearLayoutManager(context)
        domainViewModel.domainLiveData.observe(viewLifecycleOwner, Observer { initData(it) })
    }

    private fun initData(domain: Domain) {
        domain_name.text = domain.name
        domain_type.text = domain.type
        domain.domainRecords?.let { domainRecordListAdapter.setContentData(it) }
        if (domain_record_list.adapter == null) {
            domain_record_list.adapter = domainRecordListAdapter
        } else {
            domainRecordListAdapter.notifyDataSetChanged()
        }
    }
}