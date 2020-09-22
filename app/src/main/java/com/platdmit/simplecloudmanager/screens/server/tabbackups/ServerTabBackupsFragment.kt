package com.platdmit.simplecloudmanager.screens.server.tabbackups

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.platdmit.domain.models.Backup
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.databinding.FragmentServerTabBackupsBinding
import com.platdmit.simplecloudmanager.screens.server.ServerTabFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ServerTabBackupsFragment(
        private val title: String = "empty"
) : Fragment(R.layout.fragment_server_tab_backups), ServerTabFragment<ServerTabBackupsFragment> {
    private val backupsViewModel: BackupsViewModel by viewModels()
    private val backupsViewBindings: FragmentServerTabBackupsBinding by viewBinding()
    private val backupListAdapter = BackupListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backupsViewBindings.serverBackupList.layoutManager = LinearLayoutManager(context)

        backupsViewModel.backupsStateLiveData.observe(viewLifecycleOwner, ::stateHandler)
    }

    override fun getTitle(): String = title

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

    private fun updateServerBackupsData(backups: List<Backup>) {
        if (backupsViewBindings.serverBackupList.adapter == null) {
            backupsViewBindings.serverBackupList.adapter = backupListAdapter
        }
        backupListAdapter.setContentData(backups)
    }
}