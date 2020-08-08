package com.platdmit.feature_servers.screens.server

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.platdmit.feature_servers.domain.models.Server
import com.platdmit.simplecloudmanager.R
import com.platdmit.mod_servers.screens.server.tabaction.ServerTabActionsFragment
import com.platdmit.mod_servers.screens.server.tabbackups.ServerTabBackupsFragment
import com.platdmit.mod_servers.screens.server.tabloadaverage.ServerTabMainFragment
import com.platdmit.mod_servers.screens.server.tabstatistics.ServerTabStatisticsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_server.*

@AndroidEntryPoint
class ServerFragment : Fragment(R.layout.fragment_server) {
    private val serverViewModel: _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serverViewModel.serverStateLiveData.observe(viewLifecycleOwner, Observer { stateHandler(it) })

        val serverViewPagerAdapter = _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerViewStateAdapter.Companion.build(childFragmentManager, lifecycle, requireArguments().getLong("ELEMENT_ID"))

        serverViewPagerAdapter?.let {
            view_pager.adapter = it
            TabLayoutMediator(tabs, view_pager, TabConfigurationStrategy {
                tab: TabLayout.Tab, position: Int -> tab.text = it.getPageTitle(position)
            }).attach()
        }
     }

    private fun stateHandler(serverState: _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerState){
        when(serverState){
            is _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerState.Loading -> {}
            is _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerState.Success -> {
                initData(serverState.server)
            }
            is _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerState.Empty -> {}
            is _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerState.Error -> {}
        }
    }

    private fun setStateInstance(stateInstance: _root_ide_package_.com.platdmit.feature_servers.screens.server.ServerViewModel.StateIntent){
        serverViewModel.setStateIntent(stateInstance)
    }

    @SuppressLint("SetTextI18n")
    private fun initData(server: _root_ide_package_.com.platdmit.feature_servers.domain.models.Server) {
        server_image_name.text = server.name
        server_ip.text = resources.getString(R.string.server_ip_pref) + server.v4Ip
        server_status.text = server.status
        server_id.text = resources.getString(R.string.server_id_pref) + server.id
        server_price.text = server.paymentPrice
        server_paymentDate.text = server.paymentDate
        activity?.actionBar?.title = server.name
    }
}