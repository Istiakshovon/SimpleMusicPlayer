package com.music.player.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.music.player.MainActivity;
import com.music.player.MainFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private MainActivity activite;

    public SectionsPagerAdapter(FragmentManager fm, MainActivity activite) {
        super(fm);
        this.activite = activite;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return MainFragment.newInstance(position + 1,activite);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Titres";
            case 1:
                return "Album";
            case 2:
                return "Artistes";
        }
        return null;
    }
}