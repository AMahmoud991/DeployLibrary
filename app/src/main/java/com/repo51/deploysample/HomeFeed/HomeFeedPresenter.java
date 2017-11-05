package com.repo51.deploysample.HomeFeed;

import android.app.Activity;
import android.graphics.Color;

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
        prepareForTheView(feedModelList);
        feedModels.addAll(feedModelList);
        viewCallback.toggleLoadingIndicator(false);
        viewCallback.notifyDataSetChanged();
    }

    @Override
    public void firstLoad() {
        feedModels.clear();
        viewCallback.toggleLoadingIndicator(true);
        homeFeedRepository.resetStart();
        homeFeedRepository.getFeedData(url);
    }

    private void prepareForTheView(List<FeedModel> feedModelList) {
     for(FeedModel feedModel:feedModelList){
feedModel.setTextColor(getNegativePaintType(feedModel.getColor()));
     }

    }
    private String  getNegativePaintType(String hexa) {
        //hexa = "#28cb43";
        int color = Color.parseColor(hexa);
        color=(color & 0xFF000000) | (~color & 0x00FFFFFF);
        return String.format("#%06X", (0xFFFFFF & color));
    }
}
