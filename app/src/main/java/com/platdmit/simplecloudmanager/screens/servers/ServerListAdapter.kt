package com.platdmit.simplecloudmanager.screens.servers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.domain.models.Server
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.databinding.FragmentServersItemBinding
import com.platdmit.simplecloudmanager.screens.servers.ServerListAdapter.ServerListHolder

class ServerListAdapter : RecyclerView.Adapter<ServerListHolder>() {
    private val elementList: MutableList<Server> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = FragmentServersItemBinding.inflate(layoutInflater, parent, false)
        return ServerListHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ServerListHolder, position: Int) {
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun setContentData(elements: List<Server>) {
        elementList.clear()
        elementList.addAll(elements)
    }

    inner class ServerListHolder(
            val viewBinding: FragmentServersItemBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(data: Server) {
            viewBinding.run {
                serverName.text = data.name
                serverUptime.text = data.uptime
                serverId.text = data.id.toString()
                serverStatus.text = data.status

                root.setOnClickListener {
                    it.findNavController().navigate(R.id.serverFragment, bundleOf("ELEMENT_ID" to data.id))
                }
            }
        }
    }
}