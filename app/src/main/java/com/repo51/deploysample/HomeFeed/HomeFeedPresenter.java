package com.repo51.deploysample.HomeFeed;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class HomeFeedPresenter implements HomeFeedContract.Presenter, HomeFeedContract.PresenterCallback {
    private HomeFeedRepository homeFeedRepository;
    private HomeFeedContract.ViewCallback viewCallback;
    private ArrayList<FeedModel> feedModels;
    private String url = "https://213998c2-7c17-4e79-995c-574ab20e70d1.mock.pstmn.io/request";
private Activity activity;


    public ArrayList<FeedModel> getFeedModels() {
        return feedModels;
    }

    public void setFeedModels(ArrayList<FeedModel> feedModels) {
        this.feedModels = feedModels;
    }

    public HomeFeedPresenter(HomeFeedContract.ViewCallback viewCallback,Activity activity) {
        this.homeFeedRepository = new HomeFeedRepository(this,activity);
        feedModels=new ArrayList<>();
        this.viewCallback = viewCallback;
    }

    @Override
    public void loadFeedData() {
        viewCallback.toggleLoadingIndicator(true);
        homeFeedRepository.getFeedData(url);

    }

    @Override
    public void onFeedDataReterived(List<FeedModel> feedModelList) {
        feedModels.addAll(feedModelList);
        viewCallback.toggleLoadingIndicator(false);
        viewCallback.notifyDataSetChanged();
    }
}
