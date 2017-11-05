package com.repo51.deploysample.userdetails;

import android.graphics.Bitmap;

import com.repo51.deploysample.HomeFeed.FeedModel;

import java.util.List;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class UserDetailsContract {
    public interface ViewCallback {
void imageLoaded(Bitmap data);

    }

    public interface Presenter {
        void loadUserIamge(String url);

    }

    public interface PresenterCallback {
        void onUserImageLoaded(Bitmap data);

    }

    public interface Repoistory {
        void loadUserImage(String url);

    }
}
