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
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun setContentData(elements: List<DomainRecord>) {
        elementList.clear()
        elementList.addAll(elements)
        notifyDataSetChanged()
    }

    inner class DomainRecordListHolder(
            private val viewBinding: FragmentDrecordListItemBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(data: DomainRecord) {
            viewBinding.run {
                recordHost.text = data.name
                recordType.text = data.type
                recordData.text = data.data
                recordPriority.text = data.priority
                recordData.text = data.ttl
            }
        }
    }
}