package com.repo51.deploy.request;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.repo51.deploy.cashe.Cache;
import com.repo51.deploy.constants.RequestState;
import com.repo51.deploy.error.DeployError;
import com.repo51.deploy.observer.Observable;
import com.repo51.deploy.observer.RequestStateObserver;
import com.repo51.deploy.parser.BaseParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmedmahmoud on 11/1/17.
 */

public  class Request<T> implements Observable<T>,LoaderManager.LoaderCallbacks<T> {
    private int id;
    private String method;
    private String url;
    private BaseParser<T> parser;
    private int retryPolicy;
    private List<RequestStateObserver<T>> observers = new ArrayList<>();
    private Activity loaderManager;
    public Request(String method, String url, Activity loaderManager,BaseParser parser) {
        this.method = method;
        this.url = url;
        this.loaderManager=loaderManager;
        this.parser=parser;
    }

    @Override
    public void registerObserver(RequestStateObserver<T> requestStateObserver) {
        observers.add(requestStateObserver);
    }

    @Override
    public void removeObserver(RequestStateObserver<T> requestStateObserver) {
        observers.remove(requestStateObserver);
        if (observers.size() == 0) {
            cancelRequest();
        }
    }

    private void cancelRequest() {

    }

    @Override
    public void notifyObservers(int state, T data, DeployError error) {
        for (RequestStateObserver observable : observers) {
            switch (state) {
                case RequestState.SUCCESS:
                    observable.onSuccess(data);
                    break;
                case RequestState.FAIL:
                    observable.onError(error);
                    break;

            }
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(int retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public BaseParser<T> getParser() {
        return parser;
    }

    public void setParser(BaseParser<T> parser) {
        this.parser = parser;
    }

    public Activity getLoaderManager() {
        return loaderManager;
    }

    public void setLoaderManager(Activity loaderManager) {
        this.loaderManager = loaderManager;
    }

    public List<RequestStateObserver<T>> getObservers() {
        return observers;
    }

    public void setObservers(List<RequestStateObserver<T>> observers) {
        this.observers = observers;
    }

    @Override
    public Loader<T> onCreateLoader(int i, Bundle bundle) {
        return new RequestLoader(loaderManager.getBaseContext(),this);
    }

    @Override
    public void onLoadFinished(Loader<T> loader, T t) {
        notifyObservers(RequestState.SUCCESS,t,null);
    }

    @Override
    public void onLoaderReset(Loader<T> loader) {

    }
}
