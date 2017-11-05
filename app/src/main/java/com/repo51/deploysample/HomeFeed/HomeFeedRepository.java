package com.repo51.deploysample.HomeFeed;

import android.app.Activity;

import com.repo51.deploy.constants.MethodType;
import com.repo51.deploy.deploymanager.Deploy;
import com.repo51.deploy.error.DeployError;
import com.repo51.deploy.observer.RequestStateObserver;
import com.repo51.deploy.request.Request;
import com.repo51.deploy.request.RequestBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmedmahmoud on 11/4/17.
 */

public class HomeFeedRepository implements HomeFeedContract.Repoistory {
    private HomeFeedContract.PresenterCallback presenterCallback;
    private Activity activity;
    private int start = 0;

    public HomeFeedRepository(HomeFeedContract.PresenterCallback presenterCallback, Activity activity) {
        this.presenterCallback = presenterCallback;
        this.activity = activity;
    }

    @Override
    public void getFeedData(String url) {
        Request<JSONArray> request = new RequestBuilder().setUrl(url).setMethodType(MethodType.GET).setDefaultJsonArrayParser().setLoaderManager(activity).Build();
        request.registerObserver(new RequestStateObserver<JSONArray>() {
            @Override
            public void onSuccess(JSONArray data) {
                presenterCallback.onFeedDataReterived(parse(data));
            }

            @Override
            public void onError(DeployError error) {
presenterCallback.onLoadFiled();
            }
        });
        Deploy.getInstance().getDeployQueue().addRequest(request);
    }

    @Override
    public void resetStart() {
        start=0;
    }

    private List<FeedModel> parse(JSONArray data) {

        List<FeedModel> feedModels = new ArrayList<>();
        int newBegin=0;
//To Simulate Paging as the provided api dosn't provide one
        for (int i = start; i < data.length()&&(i-start)<6; i++) {
            try {
                FeedModel feedModel = new FeedModel();
                JSONObject jsonObject = data.getJSONObject(i);
                feedModel.setId(jsonObject.getString("id"));
                feedModel.setColor(jsonObject.getString("color"));
                feedModel.setLikes(jsonObject.getInt("likes"));
                feedModel.setLiked_by_user(jsonObject.getBoolean("liked_by_user"));
                UserModel userModel = new UserModel();
                JSONObject userJsonObject = jsonObject.getJSONObject("user");
                userModel.setId(userJsonObject.getString("id"));
                userModel.setUserName(userJsonObject.getString("username"));
                userModel.setProfileIamge(userJsonObject.getJSONObject("profile_image").getString("large"));
                feedModel.setUserModel(userModel);
                feedModels.add(feedModel);
                newBegin++;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        start+=newBegin;
        return feedModels;
    }
}
