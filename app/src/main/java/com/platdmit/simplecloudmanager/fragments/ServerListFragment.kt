package com.platdmit.simplecloudmanager.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.platdmit.domain.models.Server
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.adapters.ServerListAdapter
import com.platdmit.simplecloudmanager.states.ServerListState
import com.platdmit.simplecloudmanager.vm.ServerListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_servers_list.*

@AndroidEntryPoint
class ServerListFragment : Fragment(R.layout.fragment_servers_list) {
    private val serverListViewModel: ServerListViewModel by viewModels()
    private val serverListAdapter: ServerListAdapter = ServerListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        update_swipe.setOnRefreshListener {
            setStateInstance(ServerListViewModel.StateIntent.RefreshResult)
        }
        fragments_list.layoutManager = LinearLayoutManager(context)

        serverListViewModel.serversStateLiveData.observe(viewLifecycleOwner, Observer { stateHandler(it) })
        serverListViewModel.messageLiveData.observe(viewLifecycleOwner, Observer { showResultMassage(it) })
    }


    private fun stateHandler(serverListState: ServerListState){
        when(serverListState){
            is ServerListState.Loading -> {}
            is ServerListState.Success -> {
                updateAdapterData(serverListState.servers)
            }
            is ServerListState.Empty -> {}
            is ServerListState.Error -> {}
        }
    }

    private fun setStateInstance(stateInstance: ServerListViewModel.StateIntent){
        serverListViewModel.setStateIntent(stateInstance)
    }


    private fun showResultMassage(massage: String) {
        Snackbar.make(requireView(), massage, Snackbar.LENGTH_SHORT).show()
    }

    private fun updateAdapterData(servers: List<Server>) {
        serverListAdapter.setContentData(servers)
        update_swipe.isRefreshing = false
        if (fragments_list.adapter == null) {
            fragments_list.adapter = serverListAdapter
        } else {
            serverListAdapter.notifyDataSetChanged()
        }
    }
}