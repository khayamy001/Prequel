package com.torchbyte.prql.pager.fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;
import com.torchbyte.prql.pager.R;

public class SlideShowVideoFragment extends Fragment {

    int videoId;
    VideoView mVid;

    public static SlideShowVideoFragment newInstance(int id) {
        SlideShowVideoFragment f = new SlideShowVideoFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("id", id);
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
            videoId = getArguments().getInt("id");
    }

    /**
     * The Fragment is created here.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.slideshow_pager_video, container,
                false);

        mVid = (VideoView) v.findViewById(R.id.video);
        //Uri path = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + videoId);
        Uri path = Uri.parse("http://api.twitch.tv/select/imaqtpie.json?nauthsig%3D199d5d509a1257924219947c97fd0ca6677463c4%26nauth%3D%7B%5C%22user_id%5C%22%3Anull%2C%5C%22channel%5C%22%3A%5C%22imaqtpie%5C%22%2C%5C%22expires%5C%22%3A1423599806%2C%5C%22chansub%5C%22%3A%7B%5C%22view_until%5C%22%3A1924905600%2C%5C%22restricted_bitrates%5C%22%3A%5B%5D%7D%2C%5C%22private%5C%22%3A%7B%5C%22allowed_to_view%5C%22%3Atrue%7D%2C%5C%22privileged%5C%22%3Afalse%2C%5C%22source_restricted%5C%22%3Afalse%7D%26allow_source%3Dtrue%26player%3Dtwitchweb%26segment_preference%3D2");
        mVid.setVideoURI(path);
        mVid.requestFocus();

        mVid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mVid.isPlaying()){
                    mVid.pause();
                } else {
                    mVid.start();
                }
                return false;
            }
        });

        return v;
    }
}