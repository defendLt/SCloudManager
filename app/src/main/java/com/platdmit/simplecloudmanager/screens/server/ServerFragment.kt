package com.platdmit.simplecloudmanager.screens.server

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.platdmit.domain.models.Server
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.screens.server.tabactions.ServerTabActionsFragment
import com.platdmit.simplecloudmanager.screens.server.tabbackups.ServerTabBackupsFragment
import com.platdmit.simplecloudmanager.screens.server.tabloadaverage.ServerTabMainFragment
import com.platdmit.simplecloudmanager.screens.server.tabstatistics.ServerTabStatisticsFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_server.*

@AndroidEntryPoint
class ServerFragment : Fragment(R.layout.fragment_server) {
    private val serverViewModel: ServerViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serverViewModel.serverStateLiveData.observe(viewLifecycleOwner, Observer { stateHandler(it) })

        val serverViewPagerAdapter = ServerViewStateAdapter
                .add(ServerTabMainFragment(resources.getString(R.string.server_tab_main)))
                .add(ServerTabActionsFragment(resources.getString(R.string.server_tab_action)))
                .add(ServerTabBackupsFragment(resources.getString(R.string.server_tab_backups)))
                .add(ServerTabStatisticsFragment(resources.getString(R.string.server_tab_statistics)))
                .build(childFragmentManager, lifecycle, requireArguments().getLong("ELEMENT_ID"))

        serverViewPagerAdapter?.let {
            view_pager.adapter = it
            TabLayoutMediator(tabs, view_pager, TabConfigurationStrategy {
                tab: TabLayout.Tab, position: Int -> tab.text = it.getPageTitle(position)
            }).attach()
        }
     }

    private fun stateHandler(serverState: ServerState){
        when(serverState){
            is ServerState.Loading -> {}
            is ServerState.Success -> {
                initData(serverState.server)
            }
            is ServerState.Empty -> {}
            is ServerState.Error -> {}
        }
    }

    private fun setStateInstance(stateInstance: ServerViewModel.StateIntent){
        serverViewModel.setStateIntent(stateInstance)
    }

    @SuppressLint("SetTextI18n")
    private fun initData(server: Server) {
        server_image_name.text = server.name
        server_ip.text = resources.getString(R.string.server_ip_pref) + server.v4Ip
        server_status.text = server.status
        server_id.text = resources.getString(R.string.server_id_pref) + server.id
        server_price.text = server.paymentPrice
        server_paymentDate.text = server.paymentDate
        activity?.actionBar?.title = server.name
    }
}