package com.platdmit.simplecloudmanager.screens.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.domain.models.DomainRecord
import com.platdmit.simplecloudmanager.databinding.FragmentDrecordListItemBinding
import com.platdmit.simplecloudmanager.screens.domain.DomainRecordListAdapter.DomainRecordListHolder

class DomainRecordListAdapter : RecyclerView.Adapter<DomainRecordListHolder>() {
    private val elementList: MutableList<DomainRecord> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainRecordListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = FragmentDrecordListItemBinding.inflate(layoutInflater, parent, false)
        return DomainRecordListHolder(viewBinding)
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

    inner class DomainRecordListHolder(private val viewBinding: FragmentDrecordListItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(data: DomainRecord) {
            viewBinding.recordHost.text = data.name
            viewBinding.recordType.text = data.type
            viewBinding.recordData.text = data.data
            viewBinding.recordPriority.text = data.priority
            viewBinding.recordData.text = data.ttl
        }
    }
}