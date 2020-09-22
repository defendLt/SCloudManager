package com.platdmit.simplecloudmanager.screens.server.tabbackups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.domain.models.Backup
import com.platdmit.simplecloudmanager.databinding.FragmentBackupItemBinding
import com.platdmit.simplecloudmanager.screens.server.tabbackups.BackupListAdapter.BackupListHolder

class BackupListAdapter : RecyclerView.Adapter<BackupListHolder>() {
    private val elementList: MutableList<Backup> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackupListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = FragmentBackupItemBinding.inflate(layoutInflater, parent, false)
        return BackupListHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: BackupListHolder, position: Int) {
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int = elementList.size

    fun setContentData(elements: List<Backup>) {
        elementList.clear()
        elementList.addAll(elements)
        notifyDataSetChanged()
    }

    inner class BackupListHolder(
            private val viewBinding: FragmentBackupItemBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(data: Backup) {
            viewBinding.run {
                backupId.text = data.id.toString()
                backupDate.text = data.createdAt
                backupOc.text = data.distribution
                backupSize.text = data.minDiskSize
                backupPrice.text = data.priceHourly
            }
        }
    }
}