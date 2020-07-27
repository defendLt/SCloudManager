package com.platdmit.simplecloudmanager.screens.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.screens.domain.DomainRecordListAdapter.DomainRecordListHolder
import com.platdmit.domain.models.DomainRecord

class DomainRecordListAdapter : RecyclerView.Adapter<DomainRecordListHolder>() {
    private val elementList: MutableList<DomainRecord> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainRecordListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutType = R.layout.fragment_drecord_list_item
        return DomainRecordListHolder(layoutInflater, parent, layoutType)
    }

    override fun onBindViewHolder(holder: DomainRecordListHolder, position: Int) {
        elementList[position].let { holder.bindData(it) }
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun setContentData(elements: List<DomainRecord>) {
        elementList.clear()
        elementList.addAll(elements)
    }

    inner class DomainRecordListHolder(inflater: LayoutInflater, parent: ViewGroup?, layoutType: Int) : RecyclerView.ViewHolder(inflater.inflate(layoutType, parent, false)) {
        private val mHost = itemView.findViewById<TextView>(R.id.record_host)
        private val mType = itemView.findViewById<TextView>(R.id.record_type)
        private val mTtl = itemView.findViewById<TextView>(R.id.record_ttl)
        private val mPriority = itemView.findViewById<TextView>(R.id.record_priority)
        private val mData = itemView.findViewById<TextView>(R.id.record_data)
        fun bindData(data: DomainRecord) {
            mHost.text = data.name
            mType.text = data.type
            mData.text = data.data
            mPriority.text = data.priority
            mTtl.text = data.ttl
        }
    }
}