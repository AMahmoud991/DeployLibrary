package com.repo51.deploy.request;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public  class RequestLoader<T> extends AsyncTaskLoader<T> {
Request<T> request;
    public RequestLoader(Context context,Request<T> request) {
        super(context);
    this.request=request;
    }


    @Override
    public T loadInBackground() {

        return null;
    }

    @Override
    public T onLoadInBackground() {
        HttpURLConnection urlConnection = null;
        try {
            URL uri = new URL(request.getUrl());
            urlConnection = (HttpURLConnection) uri.openConnection();
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");

            int statusCode = urlConnection.getResponseCode();
            if (statusCode != 200) {
                return null;
            }


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