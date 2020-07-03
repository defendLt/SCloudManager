package com.platdmit.simplecloudmanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.adapters.ServerListAdapter.ServerListHolder
import com.platdmit.domain.models.Server

class ServerListAdapter : RecyclerView.Adapter<ServerListHolder>() {
    private lateinit var elementList: List<Server>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutType = R.layout.fragment_servers_item
        return ServerListHolder(layoutInflater, parent, layoutType)
    }

    override fun onBindViewHolder(holder: ServerListHolder, position: Int) {
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun setContentData(elements: List<Server>) {
        elementList = elements
    }

    inner class ServerListHolder(inflater: LayoutInflater, parent: ViewGroup?, layoutType: Int) : RecyclerView.ViewHolder(inflater.inflate(layoutType, parent, false)) {
        private val mImageLogo = itemView.findViewById<ImageView>(R.id.server_image_logo)
        private val mName = itemView.findViewById<TextView>(R.id.server_name)
        private val mUptime = itemView.findViewById<TextView>(R.id.server_uptime)
        private val mId = itemView.findViewById<TextView>(R.id.server_id)
        private val mStatus = itemView.findViewById<TextView>(R.id.server_status)
        fun bindData(data: Server) {
            mName.text = data.name
            mUptime.text = data.uptime
            mId.text = data.id.toString()
            mStatus.text = data.status
            itemView.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.serverFragment, bundleOf("ELEMENT_ID" to data.id))
            }
        }
    }
}