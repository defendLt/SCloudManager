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
import com.platdmit.simplecloudmanager.adapters.BackupListAdapter
import com.platdmit.simplecloudmanager.vm.BackupsViewModel
import com.platdmit.domain.models.Backup
import com.platdmit.simplecloudmanager.states.ActionState
import com.platdmit.simplecloudmanager.states.BackupsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_server_tab_backups.*

@AndroidEntryPoint
class ServerTabBackupsFragment(
        private val mTitle: String = "empty"
) : Fragment(R.layout.fragment_server_tab_backups), ServerTabFragment<ServerTabBackupsFragment> {
    private val backupsViewModel: BackupsViewModel by viewModels()
    private val backupListAdapter = BackupListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            setStateIntent(BackupsViewModel.StateIntent.SetServerId(requireArguments().getLong("ELEMENT_ID")))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        server_backup_list.layoutManager = LinearLayoutManager(context)

        backupsViewModel.backupsStateLiveData.observe(viewLifecycleOwner, Observer { stateHandler(it) })
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabBackupsFragment {
        return ServerTabBackupsFragment()
    }

    private fun stateHandler(backupsState: BackupsState){
        when(backupsState){
            is BackupsState.Loading -> {}
            is BackupsState.Success -> {
                updateServerActionData(backupsState.backups)
            }
            is BackupsState.Empty -> {}
            is BackupsState.Error -> {}
        }
    }

    private fun setStateIntent(stateIntent: BackupsViewModel.StateIntent){
        backupsViewModel.setStateIntent(stateIntent)
    }

    private fun updateServerActionData(backups: List<Backup>) {
        backupListAdapter.setContentData(backups)
        if (server_backup_list.adapter == null) {
            server_backup_list.adapter = backupListAdapter
        } else {
            backupListAdapter.notifyDataSetChanged()
        }
    }
}