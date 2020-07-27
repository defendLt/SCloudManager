package com.platdmit.simplecloudmanager.screens.server.tabbackups

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.screens.server.tabbackups.BackupListAdapter.BackupListHolder
import com.platdmit.domain.models.Backup

class BackupListAdapter : RecyclerView.Adapter<BackupListHolder>() {
    private val elementList: MutableList<Backup> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackupListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutType = R.layout.fragment_backup_item
        return BackupListHolder(layoutInflater, parent, layoutType)
    }

    override fun onBindViewHolder(holder: BackupListHolder, position: Int) {
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun setContentData(elements: List<Backup>) {
        elementList.clear()
        elementList.addAll(elements)
    }

    inner class BackupListHolder(inflater: LayoutInflater, parent: ViewGroup?, layoutType: Int) : RecyclerView.ViewHolder(inflater.inflate(layoutType, parent, false)) {
        private val mId = itemView.findViewById<TextView>(R.id.backup_id)
        private val mDate = itemView.findViewById<TextView>(R.id.backup_date)
        private val mOs = itemView.findViewById<TextView>(R.id.backup_oc)
        private val mSize = itemView.findViewById<TextView>(R.id.backup_size)
        private val mPrice = itemView.findViewById<TextView>(R.id.backup_price)

        fun bindData(data: Backup) {
            mId.text = data.id.toString()
            mDate.text = data.createdAt
            mOs.text = data.distribution
            mSize.text = data.minDiskSize
            mPrice.text = data.priceHourly
        }
    }
}