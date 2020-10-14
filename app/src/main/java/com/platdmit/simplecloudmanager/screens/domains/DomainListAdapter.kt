package com.platdmit.simplecloudmanager.screens.domains

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.domain.models.Domain
import com.platdmit.simplecloudmanager.databinding.FragmentDomainsItemBinding
import com.platdmit.simplecloudmanager.screens.domains.DomainListAdapter.DomainListHolder

class DomainListAdapter(
        private val clickListener: (Domain) -> Unit
) : RecyclerView.Adapter<DomainListHolder>() {
    private val elementList: MutableList<Domain> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DomainListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = FragmentDomainsItemBinding.inflate(layoutInflater, parent, false)
        return DomainListHolder(dataBinding){
            clickListener(elementList[it])
        }
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
        notifyDataSetChanged()
    }

    inner class DomainListHolder(
            private val viewBinding: FragmentDomainsItemBinding,
            private val onClickPosition: (Int) -> Unit
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(data: Domain) {
            viewBinding.run {
                domainName.text = data.name
                domainType.text = data.type
                itemView.setOnClickListener { onClickPosition(bindingAdapterPosition) }
            }
        }
    }
}