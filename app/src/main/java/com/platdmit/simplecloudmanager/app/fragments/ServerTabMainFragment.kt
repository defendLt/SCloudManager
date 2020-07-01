package com.platdmit.simplecloudmanager.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.app.adapters.LoadAverageListAdapter
import com.platdmit.simplecloudmanager.app.vm.LoadAverageViewModel
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory
import com.platdmit.simplecloudmanager.data.api.implement.ApiServerRepoImp
import com.platdmit.simplecloudmanager.domain.SCMApp
import com.platdmit.simplecloudmanager.domain.converters.implement.LoadAverageConvertImp
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService
import com.platdmit.simplecloudmanager.domain.models.LoadAverage
import com.platdmit.simplecloudmanager.domain.repo.ServerLoadAveragesRepo
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp
import kotlinx.android.synthetic.main.fragment_server_tab_main.*

class ServerTabMainFragment(
        private val mTitle: String = "empty"
) : Fragment(), ServerTabFragment<ServerTabMainFragment> {
    private lateinit var mLoadAverageViewModel: LoadAverageViewModel
    private val mLoadAverageListAdapter: LoadAverageListAdapter = LoadAverageListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mLoadAverageViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(LoadAverageViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    SingleElementViewModelFactory(
                            ServerRepoImp(
                                    ApiServerRepoImp(SCMApp.actualApiKeyService),
                                    SCMApp.db,
                                    LoadAverageConvertImp(),
                                    ContentUpdateService(UpdateScheduleRepImp(SCMApp.db))
                            ), ServerLoadAveragesRepo::class.java, requireArguments().getLong("ELEMENT_ID")
                    )).get(LoadAverageViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_server_tab_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        server_load_average.layoutManager = LinearLayoutManager(context)
        mLoadAverageViewModel.loadAveragesLiveData.observe(viewLifecycleOwner, Observer { updateServerActionData(it) })
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabMainFragment {
        return ServerTabMainFragment()
    }

    private fun updateServerActionData(actions: List<LoadAverage>) {
        mLoadAverageListAdapter.setContentData(actions)
        if (server_load_average.adapter == null) {
            server_load_average.adapter = mLoadAverageListAdapter
        } else {
            mLoadAverageListAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        private val TAG = ServerTabMainFragment::class.java.simpleName
    }
}