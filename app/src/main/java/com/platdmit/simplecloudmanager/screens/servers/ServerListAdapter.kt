package com.platdmit.simplecloudmanager.screens.servers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.domain.models.Server
import com.platdmit.simplecloudmanager.databinding.FragmentServersItemBinding
import com.platdmit.simplecloudmanager.screens.servers.ServerListAdapter.ServerListHolder

class ServerListAdapter(
        private val clickListener: (Server) -> Unit
) : RecyclerView.Adapter<ServerListHolder>() {
    private val elementList: MutableList<Server> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = FragmentServersItemBinding.inflate(layoutInflater, parent, false)
        return ServerListHolder(viewBinding){
            clickListener(elementList[it])
        }
    }

    override fun onBindViewHolder(holder: ServerListHolder, position: Int) {
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int = elementList.size

    fun setContentData(elements: List<Server>) {
        elementList.clear()
        elementList.addAll(elements)
        notifyDataSetChanged()
    }

    inner class ServerListHolder(
            private val viewBinding: FragmentServersItemBinding,
            private val onClickPosition: (Int) -> Unit
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(data: Server) {
            viewBinding.run {
                serverName.text = data.name
                serverUptime.text = data.uptime
                serverId.text = data.id.toString()
                serverStatus.text = data.status
                itemView.setOnClickListener { onClickPosition(bindingAdapterPosition) }
            }
        }
    }
}