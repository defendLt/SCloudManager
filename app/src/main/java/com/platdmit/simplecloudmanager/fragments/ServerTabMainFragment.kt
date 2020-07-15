package com.platdmit.simplecloudmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.adapters.LoadAverageListAdapter
import com.platdmit.simplecloudmanager.vm.LoadAverageViewModel
import com.platdmit.domain.models.LoadAverage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_server_tab_main.*

@AndroidEntryPoint
class ServerTabMainFragment(
        private val mTitle: String = "empty"
) : Fragment(R.layout.fragment_server_tab_main), ServerTabFragment<ServerTabMainFragment> {
    private val loadAverageViewModel: LoadAverageViewModel by viewModels()
    private val loadAverageListAdapter = LoadAverageListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            loadAverageViewModel.setActiveId(requireArguments().getLong("ELEMENT_ID"))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        server_load_average.layoutManager = LinearLayoutManager(context)
        loadAverageViewModel.loadAveragesLiveData.observe(viewLifecycleOwner, Observer { updateServerActionData(it) })
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabMainFragment {
        return ServerTabMainFragment()
    }

    private fun updateServerActionData(actions: List<LoadAverage>) {
        loadAverageListAdapter.setContentData(actions)
        if (server_load_average.adapter == null) {
            server_load_average.adapter = loadAverageListAdapter
        } else {
            loadAverageListAdapter.notifyDataSetChanged()
        }
    }
}