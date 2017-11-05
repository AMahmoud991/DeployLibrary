package com.repo51.deploysample.userdetails;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.repo51.deploysample.HomeFeed.FeedModel;
import com.repo51.deploysample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsContract.ViewCallback {
    public static final String FEED_MODEL_EXTRA = "feed_model";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fav_fab)
    FloatingActionButton fab;
    @BindView(R.id.header_image)
    ImageView headerImageView;
@BindView(R.id.user_details_container)
CoordinatorLayout userDetailsContainer;

    @OnClick(R.id.fav_fab)
    void onClick(View view){
        Snackbar snackbar = Snackbar
                .make(userDetailsContainer, getString(R.string.fav), Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    private FeedModel feedModel;

    private UserDetailsPresenter userDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        feedModel = (FeedModel) getIntent().getParcelableExtra(FEED_MODEL_EXTRA);
        initPresenter();
        userDetailsPresenter.loadUserIamge(feedModel.getUserModel().getProfileIamge());
        setTitle(feedModel.getUserModel().getUserName());

    }

    private void initPresenter() {
        userDetailsPresenter = new UserDetailsPresenter(this, this);
    }

    @Override
    public void imageLoaded(Bitmap data) {
        headerImageView.setImageBitmap(data);
    }
}
