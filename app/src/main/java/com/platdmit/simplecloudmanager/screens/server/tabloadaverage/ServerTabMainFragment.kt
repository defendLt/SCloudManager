package com.platdmit.simplecloudmanager.screens.server.tabloadaverage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.platdmit.domain.models.LoadAverage
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.screens.server.ServerTabFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_server_tab_main.*

@AndroidEntryPoint
class ServerTabMainFragment(
        private val mTitle: String = "empty"
) : Fragment(R.layout.fragment_server_tab_main), ServerTabFragment<ServerTabMainFragment> {
    private val loadAverageViewModel: LoadAverageViewModel by viewModels()
    private val loadAverageListAdapter = LoadAverageListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        server_load_average.layoutManager = LinearLayoutManager(context)
        loadAverageViewModel.loadAverageStateLiveData.observe(viewLifecycleOwner, Observer { stateHandler(it) })
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
        if (server_load_average.adapter == null) {
            server_load_average.adapter = loadAverageListAdapter
        } else {
            loadAverageListAdapter.notifyDataSetChanged()
        }
    }
}