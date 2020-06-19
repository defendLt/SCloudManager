package com.platdmit.simplecloudmanager.app.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.app.adapters.DomainListAdapter.DomainListHolder
import com.platdmit.simplecloudmanager.domain.models.Domain

class DomainListAdapter : RecyclerView.Adapter<DomainListHolder>() {
    private lateinit var elementList: List<Domain>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutType = R.layout.fragment_domains_item
        return DomainListHolder(layoutInflater, parent, layoutType)
    }

    override fun onBindViewHolder(holder: DomainListHolder, position: Int) {
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun setContentData(elements: List<Domain>) {
        elementList = elements
    }

    inner class DomainListHolder(inflater: LayoutInflater, parent: ViewGroup?, layoutType: Int) : RecyclerView.ViewHolder(inflater.inflate(layoutType, parent, false)) {
        private val mName = itemView.findViewById<TextView>(R.id.domain_name)
        private val mType = itemView.findViewById<TextView>(R.id.domain_type)
        fun bindData(data: Domain) {
            mName.text = data.name
            mType.text = data.type
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putLong("ELEMENT_ID", data.id)
                Navigation.findNavController(it).navigate(R.id.domainFragment, bundle)
            }
        }
    }
}