package com.repo51.deploysample.HomeFeed;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class HomeFeedRepository implements HomeFeedContract.Repoistory {
    private HomeFeedContract.PresenterCallback presenterCallback;

    public HomeFeedRepository(HomeFeedContract.PresenterCallback presenterCallback) {
        this.presenterCallback = presenterCallback;
    }

    @Override
    public void getFeedData() {

    }
}
