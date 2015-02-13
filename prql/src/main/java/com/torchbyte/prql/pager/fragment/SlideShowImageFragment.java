package com.torchbyte.prql.pager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.torchbyte.prql.pager.R;

public class SlideShowImageFragment extends Fragment {

    int mNum = 0;

    /**
     * Create a new instance of CountingFragment, providing "num" as an
     * argument.
     */
    public static SlideShowImageFragment newInstance(int num) {
        SlideShowImageFragment f = new SlideShowImageFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mNum = getArguments().getInt("num");
    }

    /**
     * The Fragment is created here.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.slideshow_pager_image, container,
                false);

        ImageView x = (ImageView) v.findViewById(R.id.pagerItemImage);
        x.setImageResource(mNum);

        return x;
    }
}