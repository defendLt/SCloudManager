package com.platdmit.simplecloudmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.adapters.BackupListAdapter
import com.platdmit.simplecloudmanager.vm.BackupsViewModel
import com.platdmit.simplecloudmanager.vm.factory.SingleElementViewModelFactory
import com.platdmit.data.api.implement.ApiServerRepoImp
import com.platdmit.simplecloudmanager.SCMApp
import com.platdmit.domain.converters.implement.BackupConvertImp
import com.platdmit.domain.helpers.ContentUpdateService
import com.platdmit.domain.models.Backup
import com.platdmit.domain.repo.ServerBackupRepo
import com.platdmit.domain.repo.implement.ServerRepoImp
import com.platdmit.domain.repo.implement.UpdateScheduleRepImp
import kotlinx.android.synthetic.main.fragment_server_tab_backups.*

class ServerTabBackupsFragment(
        private val mTitle: String = "empty"
) : Fragment(), ServerTabFragment<ServerTabBackupsFragment> {
    private lateinit var mBackupsViewModel: BackupsViewModel
    private val mAdapter: BackupListAdapter = BackupListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBackupsViewModel = if (savedInstanceState != null) {
            ViewModelProvider(this).get(BackupsViewModel::class.java)
        } else {
            ViewModelProvider(this,
                    SingleElementViewModelFactory(
                            ServerRepoImp(
                                    ApiServerRepoImp(SCMApp.actualApiKeyService),
                                    SCMApp.db,
                                    BackupConvertImp(),
                                    ContentUpdateService(UpdateScheduleRepImp(SCMApp.db))
                            ), ServerBackupRepo::class.java, requireArguments().getLong("ELEMENT_ID")
                    )).get(BackupsViewModel::class.java)
        }

        return inflater.inflate(R.layout.fragment_server_tab_backups, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        server_backup_list.layoutManager = LinearLayoutManager(context)

        mBackupsViewModel.backupsLiveData.observe(viewLifecycleOwner, Observer { updateServerActionData(it) })
    }

    override fun getTitle(): String {
        return mTitle
    }

    override fun getInstance(): ServerTabBackupsFragment {
        return ServerTabBackupsFragment()
    }

    private fun updateServerActionData(backups: List<Backup>) {
        mAdapter.setContentData(backups)
        if (server_backup_list.adapter == null) {
            server_backup_list.adapter = mAdapter
        } else {
            mAdapter.notifyDataSetChanged()
        }
    }

    companion object {
        private val TAG = ServerTabBackupsFragment::class.java.simpleName
    }
}