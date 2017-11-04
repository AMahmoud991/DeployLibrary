package com.repo51.deploysample.HomeFeed;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.repo51.deploysample.R;
import com.repo51.deploysample.Utils.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFeedActivity extends AppCompatActivity implements HomeFeedContract.ViewCallback{


    @BindView(R.id.imagesRecyclerView)
    RecyclerView imagesRecycleView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
        feedAdapter=new FeedAdapter(this,homeFeedPresenter.getFeedModels());
        imagesRecycleView.setAdapter(feedAdapter);
        homeFeedPresenter.loadFeedData();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initPresenter() {
        homeFeedPresenter=new HomeFeedPresenter(this,this);
    }

    @Override
    public void toggleLoadingIndicator(boolean show) {
        if(show){
progressBar.setVisibility(View.VISIBLE);}else {
        progressBar.setVisibility(View.GONE);}

    }

    @Override
    public void notifyDataSetChanged() {
        feedAdapter.notifyDataSetChanged();
    }
}
