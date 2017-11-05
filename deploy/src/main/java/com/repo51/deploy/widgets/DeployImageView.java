package com.repo51.deploy.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.repo51.deploy.R;
import com.repo51.deploy.constants.MethodType;
import com.repo51.deploy.deploy.Deploy;
import com.repo51.deploy.error.DeployError;
import com.repo51.deploy.observer.RequestStateObserver;
import com.repo51.deploy.request.Request;
import com.repo51.deploy.request.RequestBuilder;

/**
 * Created by ahmedmahmoud on 11/2/17.
 */

public class DeployImageView extends RelativeLayout {
    LayoutInflater mInflater;
    Activity activity;
    View v;
    ImageView tv;
    ProgressBar progressBar;

    public DeployImageView(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        init();

    }

    public DeployImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public DeployImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        init();
    }

    public void init() {
         v = mInflater.inflate(R.layout.delploy_imageview, this, true);
         tv = (ImageView) v.findViewById(R.id.imageView);
         progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
    }

    public void loadImage(String url,  Activity activity) {
        Request<Bitmap> request = new RequestBuilder()
                .setUrl(url)
                .setMethodType(MethodType.GET)
                .setDefaultImageParser()
                .setLoaderManager(activity)
                .Build();
        request.registerObserver(new RequestStateObserver<Bitmap>() {
            @Override
            public void onSuccess(Bitmap data) {
                progressBar.setVisibility(GONE);

                tv.setImageBitmap(data);
            }

            @Override
            public void onError(DeployError error) {
                progressBar.setVisibility(GONE);

                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();

            }
        });
        progressBar.setVisibility(VISIBLE);
        Deploy.getInstance().getDeployQueue().addRequest(request);

    }
}
