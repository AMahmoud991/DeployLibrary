package com.repo51.deploy.Request;

import com.repo51.deploy.Constants.RequestState;
import com.repo51.deploy.Error.DeployError;
import com.repo51.deploy.Observer.Observable;
import com.repo51.deploy.Observer.RequestStateObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmedmahmoud on 11/1/17.
 */

public abstract class Request<T> implements Observable<T> {
    private int method;
    private String url;
    private int retryPolicy;
    private List<RequestStateObserver<T>> observers = new ArrayList<>();

    public Request(int method, String url, int retryPolicy) {
        this.method = method;
        this.url = url;
        this.retryPolicy = retryPolicy;
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

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
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


}
