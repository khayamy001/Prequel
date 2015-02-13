package com.torchbyte.prql.pager.activity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.transition.Explode;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.TextView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.torchbyte.prql.pager.R;

public class DetailActivity extends ActionBarActivity implements ObservableScrollViewCallbacks {

    private static final float MAX_TEXT_SCALE_DELTA = 0.3f;
    private static final boolean TOOLBAR_IS_STICKY = true;
    private int[] bodyImages;
    private View mToolbar;
    private View mImageView;
    private View mOnClickView;
    private View mOverlayView;
    private ObservableScrollView mScrollView;
    private TextView mTitleView;
    private TextView mBodyView;
    private int mActionBarSize;
    private int mFlexibleSpaceImageHeight;
    private int mToolbarColor;
    private boolean firstTime = true;

    private int getActionBarSize() {
        TypedValue typedValue = new TypedValue();
        int[] textSizeAttr = new int[]{R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(typedValue.data, textSizeAttr);
        int actionBarSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return actionBarSize;
    }

    private int getScreenHeight() {
        return findViewById(android.R.id.content).getHeight();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailview);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        Bundle bundle = getIntent().getExtras();

        String title = bundle.getString("title");
        String body = bundle.getString("body");
        int icon = bundle.getInt("header");
        bodyImages = bundle.getIntArray("images");

        mFlexibleSpaceImageHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mActionBarSize = getActionBarSize();
        mToolbarColor = getResources().getColor(R.color.primary_dark);

        mToolbar = findViewById(R.id.toolbar);
        if (!TOOLBAR_IS_STICKY) mToolbar.setBackgroundColor(Color.TRANSPARENT);

        mOverlayView = findViewById(R.id.overlay);
        mScrollView = (ObservableScrollView) findViewById(R.id.scroll);
        mScrollView.setScrollViewCallbacks(this);

        mImageView = findViewById(R.id.image);
        mImageView.setBackground(getResources().getDrawable(icon));
        mImageView.setVisibility(View.INVISIBLE);
        mTitleView = (TextView) findViewById(R.id.title);
        mTitleView.setText(title);
        mBodyView = (TextView) findViewById(R.id.body);
        mBodyView.setText(Html.fromHtml(body));
        mBodyView.setMovementMethod(LinkMovementMethod.getInstance());
        setTitle(null);

        mOnClickView = findViewById(R.id.clickable);
        mOnClickView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, SlideshowActivity.class);
                Bundle bundle = new Bundle();

                bundle.putIntArray("images", bodyImages);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        ScrollUtils.addOnGlobalLayoutListener(mScrollView, new Runnable() {
            @Override
            public void run() {
                mScrollView.scrollTo(0, 1);
            }
        });


//        setEnterSharedElementCallback(new SharedElementCallback() {
//            View mSnapshot;
//
//            @Override
//            public void onSharedElementStart(List<String> sharedElementNames,
//                                             List<View> sharedElements, List<View> sharedElementSnapshots) {
//                for (int i = 0; i < sharedElementNames.size(); i++) {
//                    if (getResources().getString(R.string.transition_list_item).equals(sharedElementNames.get(i))) {
//                        FrameLayout element = (FrameLayout) sharedElements.get(i);
//                        mSnapshot = sharedElementSnapshots.get(i);
//                        int width = mSnapshot.getWidth();
//                        int height = mSnapshot.getHeight();
//                        int widthSpec = View.MeasureSpec.makeMeasureSpec(width,
//                                View.MeasureSpec.EXACTLY);
//                        int heightSpec = View.MeasureSpec.makeMeasureSpec(height,
//                                View.MeasureSpec.EXACTLY);
//                        mSnapshot.measure(widthSpec, heightSpec);
//                        mSnapshot.layout(0, 0, width, height);
//                        mSnapshot.setTransitionName("snapshot");
//                        element.addView(mSnapshot);
//                        break;
//                    }
//                }
//                if (mSnapshot != null) {
//                    mSnapshot.setVisibility(View.VISIBLE);
//                }
//                findViewById(R.id.image).setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onMapSharedElements(List<String> names,
//                                            Map<String, View> sharedElements) {
//                findViewById(R.id.image).setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onSharedElementEnd(List<String> sharedElementNames,
//                                           List<View> sharedElements, List<View> sharedElementSnapshots) {
//                if (mSnapshot != null) {
//                    mSnapshot.setVisibility(View.INVISIBLE);
//                }
//                findViewById(R.id.image).setVisibility(View.VISIBLE);
//            }
//        });
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus) {
        if(firstTime) {
            startRevealAnim();
            firstTime = false;
        }
    }

    private void startRevealAnim() {
        int cx = (mImageView.getLeft() + mImageView.getRight()) / 2;
        int cy = (mImageView.getTop() + mImageView.getBottom()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(mImageView.getWidth(), mImageView.getHeight());

        Animator anim = ViewAnimationUtils.createCircularReveal(mImageView, cx, cy, 0, finalRadius);
        anim.setDuration(500);
        mImageView.setVisibility(View.VISIBLE);
        anim.start();
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        // Translate overlay and image
        float flexibleRange = mFlexibleSpaceImageHeight - mActionBarSize;
        int minOverlayTransitionY = mActionBarSize - mOverlayView.getHeight();
        ViewHelper.setTranslationY(mOverlayView, ScrollUtils.getFloat(-scrollY, minOverlayTransitionY, 0));
        ViewHelper.setTranslationY(mImageView, ScrollUtils.getFloat(-scrollY / 2, minOverlayTransitionY, 0));

        // Change alpha of overlay
        ViewHelper.setAlpha(mOverlayView, ScrollUtils.getFloat((float) scrollY / flexibleRange, 0, 1));

        // Scale title text
        float scale = 1 + ScrollUtils.getFloat((flexibleRange - scrollY) / flexibleRange, 0, MAX_TEXT_SCALE_DELTA);
        ViewHelper.setPivotX(mTitleView, 0);
        ViewHelper.setPivotY(mTitleView, 0);
        ViewHelper.setScaleX(mTitleView, scale);
        ViewHelper.setScaleY(mTitleView, scale);

        // Translate title text
        int maxTitleTranslationY = (int) (mFlexibleSpaceImageHeight - mTitleView.getHeight() * scale);
        int titleTranslationY = maxTitleTranslationY - scrollY;
        if (TOOLBAR_IS_STICKY) {
            titleTranslationY = Math.max(0, titleTranslationY);
        }

        ViewHelper.setTranslationY(mTitleView, titleTranslationY);

        if (TOOLBAR_IS_STICKY) {
            // Change alpha of toolbar background
            if (-scrollY + mFlexibleSpaceImageHeight <= mActionBarSize) {
                mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(1, mToolbarColor));
            } else {
                mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, mToolbarColor));
            }
        } else {
            // Translate Toolbar
            if (scrollY < mFlexibleSpaceImageHeight) {
                ViewHelper.setTranslationY(mToolbar, 0);
            } else {
                ViewHelper.setTranslationY(mToolbar, -scrollY);
            }
        }
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
    }

}