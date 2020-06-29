package com.platdmit.simplecloudmanager.app.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.app.adapters.ServerViewStateAdapter
import com.platdmit.simplecloudmanager.app.vm.ServerViewModel
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory
import com.platdmit.simplecloudmanager.data.api.implement.ApiServerRepoImp
import com.platdmit.simplecloudmanager.domain.SCMApp
import com.platdmit.simplecloudmanager.domain.converters.implement.ServerConvertImp
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService
import com.platdmit.simplecloudmanager.domain.models.Server
import com.platdmit.simplecloudmanager.domain.repo.ServerBaseRepo
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp
import kotlinx.android.synthetic.main.fragment_server.*

class ServerFragment : Fragment() {
    private lateinit var mServerViewModel: ServerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mServerViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(ServerViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    SingleElementViewModelFactory(
                            ServerRepoImp(
                                    ApiServerRepoImp(SCMApp.actualApiKeyService),
                                    SCMApp.db,
                                    ServerConvertImp(),
                                    ContentUpdateService(UpdateScheduleRepImp(SCMApp.db))
                            ), ServerBaseRepo::class.java, requireArguments().getLong("ELEMENT_ID")
                    )).get(ServerViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_server, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mServerViewModel.serverLiveData.observe(viewLifecycleOwner, Observer { initData(it) })

        val serverViewPagerAdapter = ServerViewStateAdapter.Builder()
                .add(ServerTabMainFragment(resources.getString(R.string.server_tab_main)))
                .add(ServerTabActionsFragment(resources.getString(R.string.server_tab_action)))
                .add(ServerTabBackupsFragment(resources.getString(R.string.server_tab_backups)))
                .add(ServerTabStatisticsFragment(resources.getString(R.string.server_tab_statistics)))
                .build(childFragmentManager, lifecycle, requireArguments().getLong("ELEMENT_ID"))

        view_pager.adapter = serverViewPagerAdapter

        TabLayoutMediator(tabs, view_pager, TabConfigurationStrategy { tab: TabLayout.Tab, position: Int -> tab.text = serverViewPagerAdapter.getPageTitle(position) }).attach()
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

    companion object {
        private val TAG = ServerFragment::class.java.simpleName
    }
}