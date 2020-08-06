package com.platdmit.mod_servers.screens.server;

import android.os.Bundle;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/* @deprecated Use ServerViewStateAdapter with ViewPager2 */
@Deprecated
public class ServerViewPagerAdapter extends FragmentPagerAdapter {

    protected ArrayList<ServerTabFragment<? extends Fragment>> mServerTabFragments;
    protected static int mTabCount;
    private long mServerId;

    protected ServerViewPagerAdapter(@NonNull FragmentManager fm, int behavior, long serverId, ArrayList<ServerTabFragment<? extends Fragment>> serverTabFragments) {
        super(fm, behavior);
        mServerTabFragments = serverTabFragments;
        mTabCount = serverTabFragments.size();
        mServerId = serverId;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putLong("ELEMENT_ID", mServerId);
        Fragment fragment = (Fragment) mServerTabFragments.get(position).getInstance();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mServerTabFragments.get(position).getTitle();
    }

    public static class Builder{
        private int count;
        private final ArrayList<ServerTabFragment<? extends Fragment>> mServerTabFragments;

        public Builder(){
            mServerTabFragments = new ArrayList<>();
        }

        public Builder add(ServerTabFragment<? extends Fragment> serverTabFragment){
            mServerTabFragments.add(serverTabFragment);
            count++;
            return this;
        }

        public ServerViewPagerAdapter build(FragmentManager fm, int behavior, long serverId){
            if(count > 0){
                return new ServerViewPagerAdapter(fm, behavior, serverId, mServerTabFragments);
            } else {
                return null;
            }
        }

    }
}
