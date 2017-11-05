package com.repo51.deploysample.HomeFeed;

import java.util.List;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class HomeFeedContract {
    public interface ViewCallback {
        void toggleLoadingIndicator(boolean show);
        void notifyDataSetChanged();

    }

    public interface Presenter {
        void loadFeedData();

    }

    public interface PresenterCallback {
        void onFeedDataReterived(List<FeedModel> feedModels);

        void firstLoad();
    }

    public interface Repoistory {
        void getFeedData(String url);

        void resetStart();
    }
}
