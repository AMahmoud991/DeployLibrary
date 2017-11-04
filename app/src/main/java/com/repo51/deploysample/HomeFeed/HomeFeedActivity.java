package com.repo51.deploysample.HomeFeed;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.repo51.deploysample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFeedActivity extends AppCompatActivity implements HomeFeedContract.ViewCallback{


    @BindView(R.id.imagesRecyclerView)
    RecyclerView imagesRecycleView;
    private HomeFeedPresenter homeFeedPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
    initPresenter();
    }

    private void initPresenter() {
        homeFeedPresenter=new HomeFeedPresenter(this);
    }

    @Override
    public void toggleLoadingIndicator(boolean show) {

    }
}
