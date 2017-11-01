package com.repo51.deploy.cash;

/**
 * Created by ahmedmahmoud on 11/1/17.
 */

public interface Casher<T> {

    public void initCasher();
    public T get(String key);

    public void put(String key, T data);

    public void remove(String key);

    public void removeAll();
}
