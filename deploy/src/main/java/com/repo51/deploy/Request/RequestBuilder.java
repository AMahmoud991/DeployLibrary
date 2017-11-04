package com.repo51.deploy.request;

import android.app.LoaderManager;

import com.repo51.deploy.parser.BaseParser;
import com.repo51.deploy.parser.ImageParser;
import com.repo51.deploy.parser.JsonArrayParser;
import com.repo51.deploy.parser.JsonObjectParser;


/**
 * Created by ahmedmahmoud on 11/3/17.
 */

public class RequestBuilder {
    private String method;
    private String url;
    private BaseParser parser;
    private LoaderManager loaderManager;

    public RequestBuilder setMethodType(String method){
        this.method=method;
        return this;
    }

    public RequestBuilder setUrl(String url){
        this.url=url;
        return this;
    }
    public RequestBuilder setDefaultImageParser(){
        this.parser=new ImageParser();
        return this;
    }
    public RequestBuilder setDefaultJsonArrayParser(){
        this.parser=new JsonArrayParser();
        return this;
    }public RequestBuilder setDefaultJsonObjectParser(){
        this.parser=new JsonObjectParser();
        return this;
    }
    public RequestBuilder setCustomParser(BaseParser customParser){
        this.parser=customParser;
        return this;
    }
    public RequestBuilder setLoaderManager(LoaderManager loaderManager){
        this.loaderManager=loaderManager;
        return this;
    }
    public Request Build() {
        return new Request(method,url,loaderManager,parser);
    }

}
