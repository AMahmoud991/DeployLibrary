package com.repo51.deploysample.userdetails;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.repo51.deploy.constants.MethodType;
import com.repo51.deploy.deploy.Deploy;
import com.repo51.deploy.error.DeployError;
import com.repo51.deploy.observer.RequestStateObserver;
import com.repo51.deploy.request.Request;
import com.repo51.deploy.request.RequestBuilder;
import com.repo51.deploysample.HomeFeed.FeedModel;
import com.repo51.deploysample.HomeFeed.HomeFeedContract;
import com.repo51.deploysample.HomeFeed.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class UserDetailsRepository implements UserDetailsContract.Repoistory {
    private UserDetailsContract.PresenterCallback presenterCallback;
    private Activity activity;
    private int start = 0;

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


            }
        });
        Deploy.getInstance().getDeployQueue().addRequest(request);
    }
}