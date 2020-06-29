package com.platdmit.simplecloudmanager.app.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.platdmit.simplecloudmanager.app.fragments.ServerTabFragment

class ServerViewStateAdapter(
        private val fragmentManager: FragmentManager,
        val lifecycle: Lifecycle,
        val serverId: Long,
        private val serverTabFragments: List<ServerTabFragment<out Fragment>>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val tabCount : Int = serverTabFragments.size

    override fun getItemCount(): Int {
        return tabCount
    }

    override fun createFragment(position: Int): Fragment {
        val fragment : Fragment = serverTabFragments[position].getInstance();
        fragment.arguments = bundleOf("ELEMENT_ID" to serverId)
        return fragment;
    }

    fun getPageTitle(position : Int) : CharSequence = serverTabFragments[position].getTitle()

    companion object {
        private var count : Int = 0;
        private val serverTabFragments : MutableList<ServerTabFragment<out Fragment>> = ArrayList();

        fun add(serverTabFragment: ServerTabFragment<out Fragment>): Companion {
            serverTabFragments.add(serverTabFragment);
            count++
            return this
        }

        fun build(fm : FragmentManager, lifecycle : Lifecycle, serverId: Long) : ServerViewStateAdapter?{
            return if(count > 0){
                ServerViewStateAdapter(fm, lifecycle, serverId, serverTabFragments)
            } else {
                null
            }
        }
    }
}