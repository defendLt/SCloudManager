package com.platdmit.simplecloudmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.adapters.DomainListAdapter
import com.platdmit.simplecloudmanager.vm.DomainListViewModel
import com.platdmit.simplecloudmanager.vm.factory.ListElementsViewModelFactory
import com.platdmit.data.api.implement.ApiDomainRepoImp
import com.platdmit.simplecloudmanager.SCMApp
import com.platdmit.domain.converters.implement.DomainConvertImp
import com.platdmit.domain.helpers.ContentUpdateService
import com.platdmit.domain.models.Domain
import com.platdmit.domain.repo.DomainBaseRepo
import com.platdmit.domain.repo.implement.DomainRepoImp
import com.platdmit.domain.repo.implement.UpdateScheduleRepImp
import kotlinx.android.synthetic.main.fragment_domains_list.*

class DomainListFragment : Fragment() {
    private lateinit var mDomainListViewModel: DomainListViewModel
    private val mDomainListAdapter: DomainListAdapter = DomainListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDomainListViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(DomainListViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    ListElementsViewModelFactory(
                            DomainRepoImp(
                                    ApiDomainRepoImp(SCMApp.actualApiKeyService), SCMApp.db,
                                    DomainConvertImp(), ContentUpdateService(UpdateScheduleRepImp(SCMApp.db))
                            ), DomainBaseRepo::class.java
                    )).get(DomainListViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_domains_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragments_list.layoutManager = LinearLayoutManager(context)
        update_swipe.setOnRefreshListener{mDomainListViewModel.reloadDomainList()}

        mDomainListViewModel.domainsLiveData.observe(viewLifecycleOwner, Observer {updateAdapterData(it)})
        mDomainListViewModel.resultMassage.observe(viewLifecycleOwner, Observer {showResultMassage(it)})
    }

    private fun showResultMassage(massage: String) {
        Snackbar.make(requireView(), massage, Snackbar.LENGTH_SHORT).show()
    }

    private fun updateAdapterData(domains: List<Domain>) {
        mDomainListAdapter.setContentData(domains)
        update_swipe.isRefreshing = false

        if (fragments_list.adapter == null) {
            fragments_list.adapter = mDomainListAdapter
        } else {
            mDomainListAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        private val TAG = DomainFragment::class.java.simpleName
    }
}