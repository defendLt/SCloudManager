package com.platdmit.simplecloudmanager.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.app.adapters.DomainRecordListAdapter
import com.platdmit.simplecloudmanager.app.vm.DomainViewModel
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory
import com.platdmit.simplecloudmanager.data.api.implement.ApiDomainRepoImp
import com.platdmit.simplecloudmanager.domain.SCMApp
import com.platdmit.simplecloudmanager.domain.converters.implement.DomainConvertImp
import com.platdmit.simplecloudmanager.domain.converters.implement.DomainRecordConvertImp
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService
import com.platdmit.simplecloudmanager.domain.models.Domain
import com.platdmit.simplecloudmanager.domain.repo.DomainBaseRepo
import com.platdmit.simplecloudmanager.domain.repo.implement.DomainRepoImp
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp
import kotlinx.android.synthetic.main.fragment_domain.*

class DomainFragment : Fragment() {
    private lateinit var mDomainViewModel: DomainViewModel
    private val mDomainRecordListAdapter: DomainRecordListAdapter = DomainRecordListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDomainViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(DomainViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    SingleElementViewModelFactory(
                            DomainRepoImp(
                                    ApiDomainRepoImp(SCMApp.actualApiKeyService), SCMApp.db,
                                    DomainConvertImp(), DomainRecordConvertImp(),
                                    ContentUpdateService(UpdateScheduleRepImp(SCMApp.db))
                            ), DomainBaseRepo::class.java, requireArguments().getLong("ELEMENT_ID")
                    )).get(DomainViewModel::class.java)
            //TODO fix requireArguments
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_domain, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        domain_record_list.layoutManager = LinearLayoutManager(context)
        mDomainViewModel.domainLiveData.observe(viewLifecycleOwner, Observer { initData(it) })
    }

    private fun initData(domain: Domain) {
        domain_name.text = domain.name
        domain_type.text = domain.type
        domain.domainRecords?.let { mDomainRecordListAdapter.setContentData(it) }
        if (domain_record_list.adapter == null) {
            domain_record_list.adapter = mDomainRecordListAdapter
        } else {
            mDomainRecordListAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        private val TAG = DomainFragment::class.java.simpleName
    }
}