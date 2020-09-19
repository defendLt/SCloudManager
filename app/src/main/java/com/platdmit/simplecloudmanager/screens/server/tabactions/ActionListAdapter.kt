package com.platdmit.simplecloudmanager.screens.server.tabactions

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.domain.models.Action
import com.platdmit.simplecloudmanager.databinding.FragmentActionsItemBinding
import com.platdmit.simplecloudmanager.screens.server.tabactions.ActionListAdapter.ActionListHolder

class ActionListAdapter : RecyclerView.Adapter<ActionListHolder>() {
    private val elementList: MutableList<Action> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = FragmentActionsItemBinding.inflate(layoutInflater, parent, false)
        return ActionListHolder(dataBinding)
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

    inner class ActionListHolder(
            val viewBinding: FragmentActionsItemBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bindData(data: Action) {
            viewBinding.run {
                actionId.text = data.id.toString()
                actionStart.text = data.startedAt
                actionComplete.text = " - ${data.completedAt}"
                actionType.text = data.type
                actionInitiator.text = data.initiator
                actionStatus.text = data.status
            }
        }
    }
}