package com.repo51.deploy.request;

import android.support.v4.app.LoaderManager;

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

public abstract class Request<T> implements Observable<T> {
    private String method;
    private String url;
    private BaseParser<T> parser;
    private int retryPolicy;
    private List<RequestStateObserver<T>> observers = new ArrayList<>();
    private LoaderManager loaderManager;
    public Request(String method, String url, int retryPolicy, LoaderManager loaderManager,BaseParser parser) {
        this.method = method;
        this.url = url;
        this.retryPolicy = retryPolicy;
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
                case RequestState.START:
                    observable.onStartLoadingRequest();
                    break;
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
}
