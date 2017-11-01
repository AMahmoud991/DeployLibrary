package com.repo51.deploy.Request;

/**
 * Created by ahmedmahmoud on 11/1/17.
 */

public abstract class Request {
    private int method;
    private String url;
    private int retryPolicy;

    public Request(int method, String url,int retryPolicy) {
        this.method = method;
        this.url = url;
        this.retryPolicy=retryPolicy;
    }


    public interface MethodType{
        int POST=0;
        int GET=1;
        int UPDATE=2;

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
