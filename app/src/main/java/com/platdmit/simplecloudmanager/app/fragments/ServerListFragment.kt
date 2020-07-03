package com.platdmit.simplecloudmanager.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.google.android.material.snackbar.Snackbar
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.app.adapters.ServerListAdapter
import com.platdmit.simplecloudmanager.app.vm.ServerListViewModel
import com.platdmit.simplecloudmanager.app.vm.factory.ListElementsViewModelFactory
import com.platdmit.data.api.implement.ApiServerRepoImp
import com.platdmit.simplecloudmanager.domain.SCMApp
import com.platdmit.simplecloudmanager.domain.converters.implement.ServerConvertImp
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService
import com.platdmit.simplecloudmanager.domain.models.Server
import com.platdmit.simplecloudmanager.domain.repo.ServerBaseRepo
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp
import kotlinx.android.synthetic.main.fragment_servers_list.*

class ServerListFragment : Fragment() {
    private lateinit var mServerListViewModel: ServerListViewModel
    private val mServerListAdapter: ServerListAdapter = ServerListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mServerListViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(ServerListViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    ListElementsViewModelFactory(
                            ServerRepoImp(
                                    ApiServerRepoImp(SCMApp.actualApiKeyService),
                                    SCMApp.db,
                                    ServerConvertImp(),
                                    ContentUpdateService(UpdateScheduleRepImp(SCMApp.db))
                            ), ServerBaseRepo::class.java
                    )).get(ServerListViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_servers_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        update_swipe.setOnRefreshListener { mServerListViewModel.reloadServerList() }
        fragments_list.layoutManager = LinearLayoutManager(context)

        mServerListViewModel.serversLiveData.observe(viewLifecycleOwner, Observer { updateAdapterData(it) })
        mServerListViewModel.resultMassage.observe(viewLifecycleOwner, Observer { showResultMassage(it) })
    }

    private fun showResultMassage(massage: String) {
        Snackbar.make(requireView(), massage, Snackbar.LENGTH_SHORT).show()
    }

    private fun updateAdapterData(servers: List<Server>) {
        mServerListAdapter.setContentData(servers)
        update_swipe.isRefreshing = false
        if (fragments_list.adapter == null) {
            fragments_list.adapter = mServerListAdapter
        } else {
            mServerListAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        private val TAG = ServerListFragment::class.java.simpleName
    }
}