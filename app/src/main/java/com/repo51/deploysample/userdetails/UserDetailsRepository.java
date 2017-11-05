package com.repo51.deploysample.userdetails;

import android.app.Activity;
import android.graphics.Bitmap;

import com.repo51.deploy.constants.MethodType;
import com.repo51.deploy.deploymanager.Deploy;
import com.repo51.deploy.error.DeployError;
import com.repo51.deploy.observer.RequestStateObserver;
import com.repo51.deploy.request.Request;
import com.repo51.deploy.request.RequestBuilder;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class UserDetailsRepository implements UserDetailsContract.Repoistory {
    private UserDetailsContract.PresenterCallback presenterCallback;
    private Activity activity;

    public UserDetailsRepository(UserDetailsContract.PresenterCallback presenterCallback, Activity activity) {
        this.presenterCallback = presenterCallback;
        this.activity = activity;
    }

    @Override
    public void loadUserImage(String url) {
        Request<Bitmap> request = new RequestBuilder()
                .setUrl(url)
                .setMethodType(MethodType.GET)
                .setDefaultImageParser()
                .setLoaderManager(activity)
                .Build();
        request.registerObserver(new RequestStateObserver<Bitmap>() {
            @Override
            public void onSuccess(Bitmap data) {

                presenterCallback.onUserImageLoaded(data);
            }

            @Override
            public void onError(DeployError error) {
presenterCallback.onLoadFailed();

            }
        });
        Deploy.getInstance().getDeployQueue().addRequest(request);
    }
}