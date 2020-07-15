package com.platdmit.simplecloudmanager.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.adapters.ServerViewStateAdapter
import com.platdmit.simplecloudmanager.vm.ServerViewModel
import com.platdmit.domain.models.Server
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_server.*

@AndroidEntryPoint
class ServerFragment : Fragment(R.layout.fragment_server) {
    private val serverViewModel: ServerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            serverViewModel.setActiveId(requireArguments().getLong("ELEMENT_ID"))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        serverViewModel.serverLiveData.observe(viewLifecycleOwner, Observer { initData(it) })

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