package com.torchbyte.prql.pager.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import com.torchbyte.prql.pager.R;
import com.torchbyte.prql.pager.fragment.SlideShowImageFragment;
import com.torchbyte.prql.pager.fragment.SlideShowVideoFragment;
import com.torchbyte.prql.pager.ui.DepthPageTransformer;

public class SlideshowActivity extends FragmentActivity {

    private int[] slideShowItems;

    private ViewPager mPager;

    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        Bundle bundle = getIntent().getExtras();
        slideShowItems = bundle.getIntArray("images");

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPageTransformer(true, new DepthPageTransformer());
        mPager.setAdapter(mPagerAdapter);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        Resources res = getResources();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            for (int i = 0; i < slideShowItems.length; i++) {
                if (res.getResourceTypeName(slideShowItems[i]).equals("raw")) {
                    return SlideShowVideoFragment.newInstance(slideShowItems[i]);
                }
            }

            return SlideShowImageFragment.newInstance(slideShowItems[position]);
        }

        @Override
        public int getCount() {
            return slideShowItems.length;
        }
    }
}
