package com.repo51.deploy.observers;

import com.repo51.deploy.errors.DeployError;

/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public interface Observable<T> {
    void registerObserver(RequestStateObserver<T> repositoryObserver);
    void removeObserver(RequestStateObserver<T> repositoryObserver);
    void notifyObservers(int state, T data, DeployError deployError);
}
