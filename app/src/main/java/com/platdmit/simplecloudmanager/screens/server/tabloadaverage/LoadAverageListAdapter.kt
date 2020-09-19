package com.platdmit.simplecloudmanager.screens.server.tabloadaverage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.platdmit.domain.models.LoadAverage
import com.platdmit.simplecloudmanager.databinding.FragmentLoadaverageItemBinding
import com.platdmit.simplecloudmanager.screens.server.tabloadaverage.LoadAverageListAdapter.LoadAverageListHolder

class LoadAverageListAdapter : RecyclerView.Adapter<LoadAverageListHolder>() {
    private val elementList: MutableList<LoadAverage> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadAverageListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = FragmentLoadaverageItemBinding.inflate(layoutInflater, parent, false)
        return LoadAverageListHolder(viewBinding)
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

    inner class LoadAverageListHolder(
            private val viewBinding: FragmentLoadaverageItemBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bindData(data: LoadAverage) {
            viewBinding.run {
                title.text = data.name
                size.text = data.total
                value.text = data.value
                progress.progress = data.percent.toInt()
            }
        }
    }
}