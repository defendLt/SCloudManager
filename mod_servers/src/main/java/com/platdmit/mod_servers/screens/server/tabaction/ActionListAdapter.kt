package com.platdmit.mod_servers.screens.server.tabaction

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.simplecloudmanager.R
import com.platdmit.mod_servers.screens.server.tabaction.ActionListAdapter.ActionListHolder
import com.platdmit.mod_servers.domain.models.Action

class ActionListAdapter : RecyclerView.Adapter<ActionListHolder>() {
    private val elementList: MutableList<Action> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutType = R.layout.fragment_actions_item
        return ActionListHolder(layoutInflater, parent, layoutType)
    }

    override fun onBindViewHolder(holder: ActionListHolder, position: Int) {
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun setContentData(elements: List<Action>) {
        elementList.clear()
        elementList.addAll(elements)
    }

    inner class ActionListHolder(inflater: LayoutInflater, parent: ViewGroup?, layoutType: Int
    ) : RecyclerView.ViewHolder(inflater.inflate(layoutType, parent, false)) {
        private val mId = itemView.findViewById<TextView>(R.id.action_id)
        private val mStartedAt = itemView.findViewById<TextView>(R.id.action_start)
        private val mCompletedAt = itemView.findViewById<TextView>(R.id.action_complete)
        private val mType = itemView.findViewById<TextView>(R.id.action_type)
        private val mInitiator = itemView.findViewById<TextView>(R.id.action_initiator)
        private val mStatus = itemView.findViewById<TextView>(R.id.action_status)

        @SuppressLint("SetTextI18n")
        fun bindData(data: Action) {
            mId.text = data.id.toString()
            mStartedAt.text = data.startedAt
            mCompletedAt.text = " - ${data.completedAt}"
            mType.text = data.type
            mInitiator.text = data.initiator
            mStatus.text = data.status
        }
    }
}