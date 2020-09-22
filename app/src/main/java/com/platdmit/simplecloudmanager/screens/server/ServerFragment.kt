package com.platdmit.simplecloudmanager.screens.server

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.platdmit.domain.models.Server
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.base.extensions.setLoaderStatus
import com.platdmit.simplecloudmanager.databinding.FragmentServerBinding
import com.platdmit.simplecloudmanager.screens.server.tabactions.ServerTabActionsFragment
import com.platdmit.simplecloudmanager.screens.server.tabbackups.ServerTabBackupsFragment
import com.platdmit.simplecloudmanager.screens.server.tabloadaverage.ServerTabMainFragment
import com.platdmit.simplecloudmanager.screens.server.tabstatistics.ServerTabStatisticsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerFragment : Fragment(R.layout.fragment_server) {
    private val serverViewModel: ServerViewModel by viewModels()
    private val serverViewBinding: FragmentServerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serverViewModel.serverStateLiveData.observe(viewLifecycleOwner, ::stateHandler)

        val serverViewPagerAdapter = ServerViewStateAdapter
                .add(ServerTabMainFragment(resources.getString(R.string.server_tab_main)))
                .add(ServerTabActionsFragment(resources.getString(R.string.server_tab_action)))
                .add(ServerTabBackupsFragment(resources.getString(R.string.server_tab_backups)))
                .add(ServerTabStatisticsFragment(resources.getString(R.string.server_tab_statistics)))
                .build(childFragmentManager, lifecycle, requireArguments().getLong("ELEMENT_ID"))

        serverViewPagerAdapter?.let {
            serverViewBinding.viewPager.adapter = it
            TabLayoutMediator(serverViewBinding.tabs, serverViewBinding.viewPager, TabConfigurationStrategy {
                tab: TabLayout.Tab, position: Int -> tab.text = it.getPageTitle(position)
            }).attach()
        }
     }

    private fun stateHandler(serverState: ServerState){
        when(serverState){
            is ServerState.Loading -> {
                setLoaderStatus(true)
            }
            is ServerState.Success -> {
                setLoaderStatus(false)
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
        serverViewBinding.run {
            serverImageName.text = server.name
            serverStatus.text = server.status
            serverId.text = resources.getString(R.string.server_id_pref) + id
            serverPrice.text = server.paymentPrice
            serverPaymentDate.text = server.paymentDate
        }

        activity?.actionBar?.title = server.name
    }
}