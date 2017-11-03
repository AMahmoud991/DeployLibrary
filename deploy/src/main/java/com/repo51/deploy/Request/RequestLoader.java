package com.repo51.deploy.Request;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public abstract class RequestLoader<T> extends AsyncTaskLoader<T> {
Request<T> request;
    public RequestLoader(Context context,Request<T> request) {
        super(context);
    this.request=request;
    }

    @Override
    protected T onLoadInBackground() {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(request.getUrl());
            urlConnection.setRequestMethod(request.getMethod());
            urlConnection = (HttpURLConnection) uri.openConnection();

            int statusCode = urlConnection.getResponseCode();
            /*if (statusCode != HttpStatus.SC_OK) {
                return null;
            }*/

            InputStream inputStream = urlConnection.getInputStream();

            if (inputStream != null) {

                return request.getParser().parse(inputStream);
            }
        } catch (Exception e) {
            urlConnection.disconnect();
            Log.w("ImageDownloader", "Error downloading image from " + request.getUrl());
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }
}