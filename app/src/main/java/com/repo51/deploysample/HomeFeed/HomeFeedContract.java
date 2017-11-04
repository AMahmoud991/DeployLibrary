package com.repo51.deploysample.HomeFeed;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class HomeFeedContract {
    public interface ViewCallback {
        void toggleLoadingIndicator(boolean show);

    }

    public interface Presenter {
        void loadFeedData();

    }

    public interface PresenterCallback {
        void onFeedDataReterived();

    }

    public interface Repoistory {
        void getFeedData();

    }
}
