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
import com.platdmit.simplecloudmanager.app.adapters.ActionListAdapter
import com.platdmit.simplecloudmanager.app.vm.ActionViewModel
import com.platdmit.simplecloudmanager.app.vm.factory.SingleElementViewModelFactory
import com.platdmit.data.api.implement.ApiServerRepoImp
import com.platdmit.simplecloudmanager.domain.SCMApp
import com.platdmit.simplecloudmanager.domain.converters.implement.ActionConvertImp
import com.platdmit.simplecloudmanager.domain.helpers.ContentUpdateService
import com.platdmit.simplecloudmanager.domain.models.Action
import com.platdmit.simplecloudmanager.domain.repo.ServerActionsRepo
import com.platdmit.simplecloudmanager.domain.repo.implement.ServerRepoImp
import com.platdmit.simplecloudmanager.domain.repo.implement.UpdateScheduleRepImp
import kotlinx.android.synthetic.main.fragment_server_tab_actions.*

class ServerTabActionsFragment(
        private val mTitle: String = "empty"
) : Fragment(), ServerTabFragment<ServerTabActionsFragment> {
    private lateinit var mActionViewModel: ActionViewModel
    private val mActionListAdapter: ActionListAdapter = ActionListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mActionViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(ActionViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    SingleElementViewModelFactory(
                            ServerRepoImp(
                                    ApiServerRepoImp(SCMApp.actualApiKeyService),
                                    SCMApp.db,
                                    ActionConvertImp(),
                                    ContentUpdateService(UpdateScheduleRepImp(SCMApp.db))
                            ), ServerActionsRepo::class.java, requireArguments().getLong("ELEMENT_ID")
                    )).get(ActionViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_server_tab_actions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        server_actions_list.layoutManager = LinearLayoutManager(context)

        mActionViewModel.actionsLiveData.observe(viewLifecycleOwner, Observer { actions: List<Action> -> updateServerActionData(actions) })
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabActionsFragment {
        return ServerTabActionsFragment()
    }

    private fun updateServerActionData(actions: List<Action>) {
        mActionListAdapter.setContentData(actions)
        if (server_actions_list.adapter == null) {
            server_actions_list.adapter = mActionListAdapter
        } else {
            mActionListAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        private val TAG = ServerTabActionsFragment::class.java.simpleName
    }
}