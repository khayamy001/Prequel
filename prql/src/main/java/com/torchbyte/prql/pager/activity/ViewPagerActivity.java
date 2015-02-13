package com.torchbyte.prql.pager.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Window;
import com.torchbyte.prql.pager.R;
import com.torchbyte.prql.pager.fragment.SlidingTabsColorsFragment;

public class ViewPagerActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            SlidingTabsColorsFragment fragment = new SlidingTabsColorsFragment();
            transaction.replace(R.id.content_fragment, fragment);
            transaction.commit();
        }
    }
}


