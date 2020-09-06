package com.platdmit.simplecloudmanager.screens.servers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.platdmit.domain.models.Server
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.base.extensions.showResultMessage
import com.platdmit.simplecloudmanager.databinding.FragmentServersListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerListFragment : Fragment(R.layout.fragment_servers_list) {
    private val serverListViewModel: ServerListViewModel by viewModels()
    private val serverListViewBinding: FragmentServersListBinding by viewBinding()
    private val serverListAdapter: ServerListAdapter = ServerListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serverListViewBinding.updateSwipe.setOnRefreshListener {
            setStateInstance(ServerListViewModel.StateIntent.RefreshResult)
        }
        serverListViewBinding.fragmentsList.layoutManager = LinearLayoutManager(context)

        serverListViewModel.serversStateLiveData.observe(viewLifecycleOwner, { stateHandler(it) })
        serverListViewModel.messageLiveData.observe(viewLifecycleOwner, { showResultMessage(it) })
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

    private fun updateAdapterData(servers: List<Server>) {
        serverListAdapter.setContentData(servers)
        serverListViewBinding.updateSwipe.isRefreshing = false
        if (serverListViewBinding.fragmentsList.adapter == null) {
            serverListViewBinding.fragmentsList.adapter = serverListAdapter
        } else {
            serverListAdapter.notifyDataSetChanged()
        }
    }
}