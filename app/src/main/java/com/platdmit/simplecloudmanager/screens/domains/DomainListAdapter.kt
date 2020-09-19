package com.platdmit.simplecloudmanager.screens.domains

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.domain.models.Domain
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.databinding.FragmentDomainsItemBinding
import com.platdmit.simplecloudmanager.screens.domains.DomainListAdapter.DomainListHolder

class DomainListAdapter : RecyclerView.Adapter<DomainListHolder>() {
    private val elementList: MutableList<Domain> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = FragmentDomainsItemBinding.inflate(layoutInflater, parent, false)
        return DomainListHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: DomainListHolder, position: Int) {
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun setContentData(elements: List<Domain>) {
        elementList.clear()
        elementList.addAll(elements)
    }

    inner class DomainListHolder(
            val viewBinding: FragmentDomainsItemBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(data: Domain) {
            viewBinding.run {
                domainName.text = data.name
                domainType.text = data.type
                root.setOnClickListener {
                    it.findNavController().navigate(R.id.domainFragment, bundleOf("ELEMENT_ID" to data.id))
                }
            }
        }
    }
}