package com.rupesh.baji.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.rupesh.baji.R;
import com.rupesh.baji.adapters.ViewPageAdapterChallenge;

public class Challenges extends Fragment {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentActivity myContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_challenges, container, false);

        tabLayout = view.findViewById(R.id.tab_register);
        viewPager = view.findViewById(R.id.viewpager);

        ViewPageAdapterChallenge viewPageAdapter = new ViewPageAdapterChallenge(getFragmentManager());

        viewPageAdapter.addFragment(new MyChallenges(), "My Challenges");
        viewPageAdapter.addFragment(new CurrentChallenges(), "Ongoing");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}