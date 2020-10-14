package com.platdmit.simplecloudmanager.screens.servers

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.platdmit.domain.models.Server
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.databinding.FragmentServersListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerListFragment : Fragment(R.layout.fragment_servers_list) {
    private val serverListViewModel: ServerListViewModel by viewModels()
    private val serverListViewBinding: FragmentServersListBinding by viewBinding()
    private val serverListAdapter: ServerListAdapter = ServerListAdapter(::recyclerItemsClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serverListViewBinding.run {
            updateSwipe.setOnRefreshListener {
                setStateInstance(ServerListViewModel.StateIntent.RefreshResult)
            }
            fragmentsList.layoutManager = LinearLayoutManager(context)
        }

        serverListViewModel.serversStateLiveData.observe(viewLifecycleOwner, ::stateHandler)
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
        serverListViewBinding.run {
            updateSwipe.isRefreshing = false

            if (fragmentsList.adapter == null) {
                fragmentsList.adapter = serverListAdapter
            }
        }

        serverListAdapter.setContentData(servers)
    }

    private fun recyclerItemsClickListener(server: Server){
        findNavController().navigate(R.id.serverFragment, bundleOf("ELEMENT_ID" to server.id))
    }
}