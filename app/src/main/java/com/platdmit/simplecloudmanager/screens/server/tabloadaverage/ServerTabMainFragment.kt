package com.platdmit.simplecloudmanager.screens.server.tabloadaverage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.platdmit.domain.models.LoadAverage
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.databinding.FragmentServerTabMainBinding
import com.platdmit.simplecloudmanager.screens.server.ServerTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerTabMainFragment(
        private val mTitle: String = "empty"
) : Fragment(R.layout.fragment_server_tab_main), ServerTabFragment<ServerTabMainFragment> {
    private val loadAverageViewModel: LoadAverageViewModel by viewModels()
    private val loadAverageViewBinding: FragmentServerTabMainBinding by viewBinding()
    private val loadAverageListAdapter = LoadAverageListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadAverageViewBinding.serverLoadAverage.layoutManager = LinearLayoutManager(context)
        loadAverageViewModel.loadAverageStateLiveData.observe(viewLifecycleOwner, { stateHandler(it) })
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabMainFragment {
        return ServerTabMainFragment()
    }

    private fun stateHandler(loadAverageState: LoadAverageState){
        when(loadAverageState){
            is LoadAverageState.Loading -> {}
            is LoadAverageState.Success -> {
                updateServerLoadAverageData(loadAverageState.loadAverages)
            }
            is LoadAverageState.Empty -> {}
            is LoadAverageState.Error -> {}
        }
    }

    private fun setStateInstance(stateInstance: LoadAverageViewModel.StateIntent){
        loadAverageViewModel.setStateIntent(stateInstance)
    }

    private fun updateServerLoadAverageData(loadAverages: List<LoadAverage>) {
        loadAverageListAdapter.setContentData(loadAverages)
        if (loadAverageViewBinding.serverLoadAverage.adapter == null) {
            loadAverageViewBinding.serverLoadAverage.adapter = loadAverageListAdapter
        } else {
            loadAverageListAdapter.notifyDataSetChanged()
        }
    }
}