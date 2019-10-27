package com.example.api_good.views.Frag_ments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class View_Pager extends FragmentPagerAdapter {
    private final List<Fragment> fragmentlist=new ArrayList<>();
    private final List<String> fragmentlisttitels=new ArrayList<>();
    public View_Pager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentlist.get(i);
    }

    @Override
    public int getCount() {
        return fragmentlisttitels.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentlisttitels.get(position);
    }
    public void addFragment (Fragment fragment , String Title){
        fragmentlist.add(fragment);
        fragmentlisttitels.add(Title);
    }
}
