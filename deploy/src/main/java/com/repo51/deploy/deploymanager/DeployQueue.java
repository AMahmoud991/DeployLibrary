package com.repo51.deploy.deploymanager;


import android.content.Loader;

import com.repo51.deploy.constants.RequestState;
import com.repo51.deploy.observers.RequestStateObserver;
import com.repo51.deploy.request.Request;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ahmedmahmoud on 11/2/17.
 */

public class DeployQueue {
    private static DeployQueue instance;
    private AtomicInteger atomicInteger=new AtomicInteger();
    private HashMap<String,Request> queueRequests;
    private DeployQueue(){
        queueRequests= new HashMap<>();
    }

    public static synchronized DeployQueue getInstance() {
        if (instance == null) {
            instance = new DeployQueue();
        }
        return instance;
    }

    public void addRequest(Request request) {
       if(!checkIfInCache(request)){
           addToRequestQueue(request);
       }
      //  queueRequests.add(request);
    }

    private void startRequestIfNotStarted(Request request) {
      Loader loader= queueRequests.get(request.getUrl()).getLoaderManager().getLoaderManager().initLoader(atomicInteger.addAndGet(1),null,request);
  request.setId(atomicInteger.get());
    loader.forceLoad();
    }

    private void addToRequestQueue(Request request) {
        if(queueRequests.get(request.getUrl())==null){
            queueRequests.put(request.getUrl(),request);
            startRequestIfNotStarted(request);

        }else{
            queueRequests.get(request.getUrl()).registerObserver((RequestStateObserver) request.getObservers().get(0));
        }
    }

    private boolean checkIfInCache(Request request) {
        if(Deploy.getInstance().getRequestCache().get(request.getUrl())==null){
            return false;
        }else {
            request.notifyObservers(RequestState.SUCCESS,Deploy.getInstance().getRequestCache().get(request.getUrl()),null);
        return true;
        }
    }

    public void cancelRequest(Request request) {
        Request currentRequest=queueRequests.get(request.getUrl());
        if(currentRequest!=null){
            currentRequest.removeObserver((RequestStateObserver) request.getObservers().get(0));
            if(currentRequest.getObservers().size()==0){
                currentRequest.getLoaderManager().getLoaderManager().destroyLoader(currentRequest.getId());
                queueRequests.remove(request.getUrl());
            }
        }

    }
}
