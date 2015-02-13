package com.torchbyte.prql.pager.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.torchbyte.prql.pager.R;
import com.torchbyte.prql.pager.activity.DetailActivity;
import com.torchbyte.prql.pager.adapter.RecyclerAdapter;
import com.torchbyte.prql.pager.adapter.RecyclerItemClickListener;
import com.torchbyte.prql.pager.model.Data;
import com.torchbyte.prql.pager.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContentFragment extends Fragment {

    private static final String KEY_DATA = "data";
    private List<Data> data = Collections.emptyList();

    /**
     * @return a new instance of {@link ContentFragment}, adding the parameters into a bundle and
     * setting them as arguments.
     */
    public static ContentFragment newInstance(List<Data> mData) {
        Bundle bundle = new Bundle();

        ArrayList data = (ArrayList) mData;
        bundle.putParcelableArrayList(KEY_DATA, data);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_item, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle args = getArguments();

        if (args != null) {
            data = args.getParcelableArrayList(KEY_DATA);
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(view.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        Bundle bundle = new Bundle();

                        Data item = data.get(position);

                        String title = item.title;
                        String body = item.body;
                        int header = item.headerIconUrl;
                        int thumbnail = item.thumbnailIconUrl;
                        int[] textImages = item.slideShowItems;

                        bundle.putString("title", title);
                        bundle.putString("body", body);
                        bundle.putInt("header", header);
                        bundle.putInt("thumb", thumbnail);
                        bundle.putIntArray("images", textImages);

                        intent.putExtras(bundle);

                        //todo: Wait for the stackoverflow answer or more research: https://stackoverflow.com/questions/28157421/android-overriding-shared-element-return-animation
//                        ActivityOptionsCompat options = ActivityOptionsCompat
//                                .makeSceneTransitionAnimation(getActivity(),
//                                        view.findViewById(R.id.icon),
//                                        getString(R.string.transition_list_item));

                        ActivityCompat.startActivity(getActivity(), intent, bundle);
                    }
                })
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerAdapter mAdapter = new RecyclerAdapter(data);
        recyclerView.setAdapter(mAdapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}

