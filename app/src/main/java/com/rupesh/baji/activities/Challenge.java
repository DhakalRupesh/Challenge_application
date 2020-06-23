package com.rupesh.baji.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.rupesh.baji.R;
import com.rupesh.baji.adapters.ViewPageAdapterChallenge;
import com.rupesh.baji.fragments.AddChallenge;
import com.rupesh.baji.fragments.CurrentChallenges;
import com.rupesh.baji.fragments.MyChallenges;

public class Challenge extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentActivity myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        tabLayout = findViewById(R.id.tab_register);
        viewPager = findViewById(R.id.viewpager);

        ViewPageAdapterChallenge viewPageAdapter = new ViewPageAdapterChallenge(getSupportFragmentManager());

        viewPageAdapter.addFragment(new AddChallenge(), "New Challenge");
        viewPageAdapter.addFragment(new MyChallenges(), "My Challenges");
        viewPageAdapter.addFragment(new CurrentChallenges(), "Ongoing");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}