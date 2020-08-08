package com.platdmit.feature_servers.screens.server.tabbackups

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.platdmit.simplecloudmanager.R
import com.platdmit.feature_servers.domain.models.Backup
import com.platdmit.mod_servers.screens.server.ServerTabFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_server_tab_backups.*

@AndroidEntryPoint
class ServerTabBackupsFragment(
        private val mTitle: String = "empty"
) : Fragment(R.layout.fragment_server_tab_backups), ServerTabFragment<ServerTabBackupsFragment> {
    private val backupsViewModel: BackupsViewModel by viewModels()
    private val backupListAdapter = BackupListAdapter()

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
                updateServerBackupsData(backupsState.backups)
            }
            is BackupsState.Empty -> {}
            is BackupsState.Error -> {}
        }
    }

    private fun setStateIntent(stateIntent: BackupsViewModel.StateIntent){
        backupsViewModel.setStateIntent(stateIntent)
    }

    private fun updateServerBackupsData(backups: List<_root_ide_package_.com.platdmit.feature_servers.domain.models.Backup>) {
        backupListAdapter.setContentData(backups)
        if (server_backup_list.adapter == null) {
            server_backup_list.adapter = backupListAdapter
        } else {
            backupListAdapter.notifyDataSetChanged()
        }
    }
}