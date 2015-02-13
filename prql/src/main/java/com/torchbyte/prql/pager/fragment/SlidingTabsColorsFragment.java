package com.torchbyte.prql.pager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.torchbyte.prql.pager.R;
import com.torchbyte.prql.pager.model.Data;
import com.torchbyte.prql.pager.ui.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class SlidingTabsColorsFragment extends Fragment {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private ImageView mHeaderView;
    private List<PagerItem> mTabs = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int[] internshipSiemens = {R.drawable.siemens2, R.drawable.siemens3};
        int[] internshipBmy = {R.drawable.bemoreyou1, R.drawable.bemoreyou2, R.drawable.bemoreyou3};
        int[] internshipYt = {R.drawable.youngtalent1, R.drawable.youngtalent2, R.drawable.youngtalent3, R.drawable.youngtalent4};
        int[] internshipFilm = {R.raw.mv_film};
        int[] internshipbranding = {};
        int[] internshipMobile = {R.drawable.mobile1, R.drawable.mobile2};
        int[] internshipCards = {R.drawable.card1, R.drawable.card2};

        int[] internshipAboutCompany = {R.drawable.housestyle1};
        int[] internshipHomestyle = {};
        int[] internshipCustomers = {};

        int[] internshipExperience = {};
        int[] internshipAppendix = {};

        List<Data> internshipList = new ArrayList<>();
        internshipList.add(new Data(getString(R.string.internship_siemens), getString(R.string.internship_siemens_body), R.drawable.icon_siemens, R.drawable.ic_siemens, internshipSiemens));
        internshipList.add(new Data(getString(R.string.internship_bmy), getString(R.string.internship_bmy_body), R.drawable.icon_bemoreyou, R.drawable.ic_bemoreyou, internshipBmy));
        internshipList.add(new Data(getString(R.string.internship_yt), getString(R.string.internship_yt_body), R.drawable.icon_youngtalent, R.drawable.ic_youngtalent, internshipYt));
        internshipList.add(new Data(getString(R.string.internship_film), getString(R.string.internship_film_body), R.drawable.icon_bedrijfsfilm, R.drawable.ic_film, internshipFilm));
        internshipList.add(new Data(getString(R.string.internship_branding), getString(R.string.internship_branding_body), R.drawable.icon_huisstijl, R.drawable.ic_huisstijl, internshipbranding));
        internshipList.add(new Data(getString(R.string.internship_mobile), getString(R.string.internship_mobile_body), R.drawable.icon_mobilesite, R.drawable.ic_mobile, internshipMobile));
        internshipList.add(new Data(getString(R.string.internship_ftdg), getString(R.string.internship_ftdg_body), R.drawable.icon_cards, R.drawable.ic_cards, internshipCards));

        List<Data> aboutMeijerList = new ArrayList<>();
        aboutMeijerList.add(new Data(getString(R.string.tab_about_company), getString(R.string.tab_about_company_body), R.drawable.icon_meijerwalters, R.drawable.ic_overmw, internshipAboutCompany));
        aboutMeijerList.add(new Data(getString(R.string.internship_homestyle), getString(R.string.internship_homestyle_body), R.drawable.icon_huisstijlmw, R.drawable.ic_housestyle, internshipHomestyle));
        aboutMeijerList.add(new Data(getString(R.string.internship_customers), getString(R.string.internship_customers_body), R.drawable.icon_klanten, R.drawable.ic_klanten, internshipCustomers));

        List<Data> aboutList = new ArrayList<>();
        aboutList.add(new Data(getString(R.string.internship_experience), getString(R.string.internship_experience_body), R.drawable.icon_overmij, R.drawable.ic_ovemij, internshipExperience));

        /**
         * Populate our tab list with tabs. Each item contains a title, indicator color and divider
         * color, which are used by {@link SlidingTabLayout}.
         */
        mTabs.add(new PagerItem(
                getString(R.string.tab_internship), // Title
                getResources().getColor(R.color.accent),
                internshipList
        ));

        mTabs.add(new PagerItem(
                getString(R.string.tab_about_company),
                getResources().getColor(R.color.accent),
                aboutMeijerList
        ));

        mTabs.add(new PagerItem(
                getString(R.string.tab_about),
                getResources().getColor(R.color.accent),
                aboutList
        ));
    }

    /**
     * Inflates the {@link View} which will be displayed by this {@link Fragment}, from the app's
     * resources.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pager, container, false);
    }

    /**
     * This is called after the {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)} has finished.
     * Here we can pick out the {@link View}s we need to configure from the content view.
     * <p/>
     * We set the {@link ViewPager}'s adapter to be an instance of
     * {@link SampleFragmentPagerAdapter}. The {@link SlidingTabLayout} is then given the
     * {@link ViewPager} so that it can populate itself.
     *
     * @param view View created in {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SampleFragmentPagerAdapter(getChildFragmentManager()));

        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);

        // Set a TabColorizer to customize the indicator and divider colors. Here we just retrieve
        // the tab at the position, and return it's set color
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {

            @Override
            public int getIndicatorColor(int position) {
                return mTabs.get(position).getIndicatorColor();
            }
        });

        mHeaderView = (ImageView) view.findViewById(R.id.header);
        mHeaderView.setImageDrawable(getResources().getDrawable(R.drawable.icon_home1));
    }

    /**
     * This class represents a tab to be displayed by {@link ViewPager} and it's associated
     * {@link SlidingTabLayout}.
     */
    static class PagerItem {
        private final CharSequence tabTitle;
        private List<Data> mData;
        private int mIndicatorColor;

        PagerItem(CharSequence title, int indicatorColor, List<Data> mData) {
            this.tabTitle = title;
            this.mIndicatorColor = indicatorColor;
            this.mData = mData;
        }

        /**
         * @return A new {@link Fragment} to be displayed by a {@link ViewPager}
         */
        Fragment createFragment() {
            return ContentFragment.newInstance(mData);
        }

        /**
         * @return the title which represents this tab. In this sample this is used directly by
         * {@link android.support.v4.view.PagerAdapter#getPageTitle(int)}
         */
        CharSequence getTitle() {
            return tabTitle;
        }

        /**
         * @return the color to be used for indicator on the {@link SlidingTabLayout}
         */
        int getIndicatorColor() {
            return mIndicatorColor;
        }

    }

    /**
     * The {@link FragmentPagerAdapter} used to display pages in this sample. The individual pages
     * are instances of {@link ContentFragment} which just display three lines of text. Each page is
     * created by the relevant {@link com.torchbyte.prql.pager.fragment.SlidingTabsColorsFragment.PagerItem} for the requested position.
     * <p/>
     * The important section of this class is the {@link #getPageTitle(int)} method which controls
     * what is displayed in the {@link SlidingTabLayout}.
     */
    class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

        SampleFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return the {@link android.support.v4.app.Fragment} to be displayed at {@code position}.
         * <p/>
         * Here we return the value returned from {@link com.torchbyte.prql.pager.fragment.SlidingTabsColorsFragment.PagerItem#createFragment()}.
         */
        @Override
        public Fragment getItem(int i) {
            return mTabs.get(i).createFragment();
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

        /**
         * Return the title of the item at {@code position}. This is important as what this method
         * returns is what is displayed in the {@link SlidingTabLayout}.
         * <p/>
         * Here we return the value returned from {@link com.torchbyte.prql.pager.fragment.SlidingTabsColorsFragment.PagerItem#getTitle()}.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabs.get(position).getTitle();
        }

    }

}