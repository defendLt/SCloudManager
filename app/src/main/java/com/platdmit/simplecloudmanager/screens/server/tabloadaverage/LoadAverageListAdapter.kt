package com.platdmit.simplecloudmanager.screens.server.tabloadaverage

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.simplecloudmanager.R
import com.platdmit.simplecloudmanager.screens.server.tabloadaverage.LoadAverageListAdapter.LoadAverageListHolder
import com.platdmit.domain.models.LoadAverage

class LoadAverageListAdapter : RecyclerView.Adapter<LoadAverageListHolder>() {
    private val elementList: MutableList<LoadAverage> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadAverageListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutType = R.layout.fragment_loadaverage_item
        return LoadAverageListHolder(layoutInflater, parent, layoutType)
    }

    override fun onBindViewHolder(holder: LoadAverageListHolder, position: Int) {
        holder.bindData(elementList[position])
    }

    override fun getItemCount(): Int {
        return elementList.size
    }

    fun setContentData(elements: List<LoadAverage>) {
        elementList.clear()
        elementList.addAll(elements)
    }

    inner class LoadAverageListHolder(inflater: LayoutInflater, parent: ViewGroup?, layoutType: Int) : RecyclerView.ViewHolder(inflater.inflate(layoutType, parent, false)) {
        private val mTitle = itemView.findViewById<TextView>(R.id.title)
        private val mSize = itemView.findViewById<TextView>(R.id.size)
        private val mValue = itemView.findViewById<TextView>(R.id.value)
        private val mPercent = itemView.findViewById<ProgressBar>(R.id.progress)
        fun bindData(data: LoadAverage) {
            mTitle.text = data.name
            mSize.text = data.total
            mValue.text = data.value
            mPercent.progress = data.percent.toInt()
        }
    }
}