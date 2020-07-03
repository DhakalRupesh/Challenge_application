package com.rupesh.baji.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.rupesh.baji.R;
import com.rupesh.baji.adapters.ViewPageAdapterChallenge;
import com.rupesh.baji.fragments.AddChallenge;
import com.rupesh.baji.fragments.CurrentChallenges;
import com.rupesh.baji.fragments.MyChallenges;

public class Challenge extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    View mIndicator;

    private int indicatorWidth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        tabLayout = findViewById(R.id.tab_register);
        viewPager = findViewById(R.id.viewpager);
        mIndicator = findViewById(R.id.indicator);

        ViewPageAdapterChallenge viewPageAdapter = new ViewPageAdapterChallenge(getSupportFragmentManager());

        viewPageAdapter.addFragment(new AddChallenge(), "New Challenge");
        viewPageAdapter.addFragment(new MyChallenges(), "My Challenges");
        viewPageAdapter.addFragment(new CurrentChallenges(), "Ongoing");

        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //Determine indicator width at runtime
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = tabLayout.getWidth() / tabLayout.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //To move the indicator as the user scroll, we will need the scroll offset values
            //positionOffset is a value from [0..1] which represents how far the page has been scrolled
            //see https://developer.android.com/reference/android/support/v4/view/ViewPager.OnPageChangeListener
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mIndicator.getLayoutParams();

                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset =  (positionOffset+position) * indicatorWidth ;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}