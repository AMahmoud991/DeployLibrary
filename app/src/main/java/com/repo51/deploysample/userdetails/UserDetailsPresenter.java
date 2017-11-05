package com.repo51.deploysample.userdetails;

import android.app.Activity;
import android.graphics.Bitmap;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class UserDetailsPresenter implements UserDetailsContract.Presenter, UserDetailsContract.PresenterCallback {
    private UserDetailsRepository userDetailsRepository;
    private UserDetailsContract.ViewCallback viewCallback;
    private Activity activity;


    public UserDetailsPresenter(UserDetailsContract.ViewCallback viewCallback, Activity activity) {
        this.userDetailsRepository = new UserDetailsRepository(this, activity);
        this.viewCallback = viewCallback;
    }

    @Override
    public void loadUserIamge(String url) {
        userDetailsRepository.loadUserImage(url);
    }

    @Override
    public void onUserImageLoaded(Bitmap data) {
        viewCallback.imageLoaded(data);
    }
}
