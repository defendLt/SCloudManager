package com.platdmit.simplecloudmanager.app.adapters;

import android.os.Bundle;

import com.platdmit.simplecloudmanager.app.fragments.ServerTabFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ServerViewStateAdapter extends FragmentStateAdapter {

    protected List<ServerTabFragment<? extends Fragment>> mServerTabFragments;
    protected static int mTabCount;
    private long mServerId;

    protected ServerViewStateAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, long serverId, List<ServerTabFragment<? extends Fragment>> serverTabFragments) {
        super(fragmentManager, lifecycle);
        mServerTabFragments = serverTabFragments;
        mTabCount = serverTabFragments.size();
        mServerId = serverId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putLong("ELEMENT_ID", mServerId);
        Fragment fragment = (Fragment) mServerTabFragments.get(position).getInstance();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return mTabCount;
    }

    public CharSequence getPageTitle(int position) {
        return mServerTabFragments.get(position).getTitle();
    }

    public static class Builder{
        private int count;
        private final List<ServerTabFragment<? extends Fragment>> mServerTabFragments;

        public Builder(){
            mServerTabFragments = new ArrayList<>();
        }

        public Builder add(ServerTabFragment<? extends Fragment> serverTabFragment){
            mServerTabFragments.add(serverTabFragment);
            count++;
            return this;
        }

        public ServerViewStateAdapter build(FragmentManager fm, Lifecycle lifecycle, long serverId){
            if(count > 0){
                return new ServerViewStateAdapter(fm, lifecycle, serverId, mServerTabFragments);
            } else {
                return null;
            }
        }

    }
}
