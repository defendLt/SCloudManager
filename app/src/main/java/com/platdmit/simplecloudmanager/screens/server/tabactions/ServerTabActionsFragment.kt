package com.platdmit.simplecloudmanager.screens.server.tabactions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.platdmit.domain.models.Action
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.databinding.FragmentServerTabActionsBinding
import com.platdmit.simplecloudmanager.screens.server.ServerTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerTabActionsFragment(
        private val mTitle: String = "empty"
) : Fragment(R.layout.fragment_server_tab_actions), ServerTabFragment<ServerTabActionsFragment> {
    private val actionViewModel: ActionViewModel by viewModels()
    private val actionViewBinding: FragmentServerTabActionsBinding by viewBinding()
    private val actionListAdapter = ActionListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionViewBinding.serverActionsList.layoutManager = LinearLayoutManager(context)

        actionViewModel.actionStateLiveData.observe(viewLifecycleOwner, { stateHandler(it) })
    }

    private fun stateHandler(actionState: ActionState){
        when(actionState){
            is ActionState.Loading -> {}
            is ActionState.Success -> {
                updateServerActionData(actionState.actions)
            }
            is ActionState.Empty -> {}
            is ActionState.Error -> {}
        }
    }

    private fun setStateInstance(stateInstance: ActionViewModel.StateIntent){
        actionViewModel.setStateIntent(stateInstance)
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabActionsFragment {
        return ServerTabActionsFragment()
    }

    private fun updateServerActionData(actions: List<Action>) {
        actionListAdapter.setContentData(actions)
        if (actionViewBinding.serverActionsList.adapter == null) {
            actionViewBinding.serverActionsList.adapter = actionListAdapter
        } else {
            actionListAdapter.notifyDataSetChanged()
        }
    }
}