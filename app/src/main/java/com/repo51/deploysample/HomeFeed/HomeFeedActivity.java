package com.repo51.deploysample.HomeFeed;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.repo51.deploysample.R;
import com.repo51.deploysample.userdetails.UserDetailsActivity;
import com.repo51.deploysample.utilsmanagers.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFeedActivity extends AppCompatActivity implements HomeFeedContract.ViewCallback, FeedAdapter.ItemCLickListener, FeedAdapter.LoadMoreListener {


    @BindView(R.id.imagesRecyclerView)
    RecyclerView imagesRecycleView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    private HomeFeedPresenter homeFeedPresenter;
    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPresenter();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.feed));

        imagesRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        imagesRecycleView.addItemDecoration(decoration);
        feedAdapter = new FeedAdapter(imagesRecycleView, this, homeFeedPresenter.getFeedModels());
        feedAdapter.setItemCLickListener(this);
        feedAdapter.setOnLoadMoreListener(this);
        imagesRecycleView.setAdapter(feedAdapter);
        homeFeedPresenter.firstLoad();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeFeedPresenter.firstLoad();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initPresenter() {
        homeFeedPresenter = new HomeFeedPresenter(this, this);
    }

    @Override
    public void toggleLoadingIndicator(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void notifyDataSetChanged() {
        swipeRefreshLayout.setRefreshing(false);

        feedAdapter.setLoadingState(false);
        feedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemCLickListener(int pos, FeedModel feedModel, View view) {
        Intent userDetailsIntent = new Intent(this, UserDetailsActivity.class);
        userDetailsIntent.putExtra(UserDetailsActivity.FEED_MODEL_EXTRA, feedModel);

        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, getString(R.string.commen_view));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            startActivity(userDetailsIntent, activityOptionsCompat.toBundle());
        else
            startActivity(userDetailsIntent);


    }

    @Override
    public void onLoadMore() {

        homeFeedPresenter.loadFeedData();
    }
}
