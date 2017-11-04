package com.repo51.deploysample.HomeFeed;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class HomeFeedPresenter implements HomeFeedContract.Presenter, HomeFeedContract.PresenterCallback {
    private HomeFeedRepository homeFeedRepository;
    private HomeFeedContract.ViewCallback viewCallback;

    public HomeFeedPresenter(HomeFeedRepository homeFeedRepository) {
        this.homeFeedRepository = homeFeedRepository;
    }

    public HomeFeedPresenter(HomeFeedContract.ViewCallback viewCallback) {
        this.homeFeedRepository = new HomeFeedRepository(this);
        this.viewCallback = viewCallback;
    }

    @Override
    public void loadFeedData() {

    }

    @Override
    public void onFeedDataReterived() {

    }
}
