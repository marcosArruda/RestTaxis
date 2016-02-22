package com.taxis.entities.describer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosarruda on 2/15/16.
 * Endpoint Object
 */
public class Endpoint {
    private String link;
    private String description;
    private List<String> requestParams = new ArrayList<String>();
    private String responseExample;
    private String method;

    public Endpoint(String link, String method, String description){
        this.link = link;
        this.method = method;
        this.description = description;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(List<String> requestParams) {
        this.requestParams = requestParams;
    }

    public void addRequestParam(String param){
        if(!this.requestParams.contains(param))
            this.requestParams.add(param);
    }

    public String getResponseExample() {
        return responseExample;
    }

    public void setResponseExample(String responseExample) {
        this.responseExample = responseExample;
    }
}
