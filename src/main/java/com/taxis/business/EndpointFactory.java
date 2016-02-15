package com.taxis.business;

import com.taxis.entities.describer.Endpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosarruda on 2/15/16.
 */
public class EndpointFactory {
    public static List<Endpoint> buildEndpoints(){
        List<Endpoint> endpoints = new ArrayList<Endpoint>();



        return endpoints;
    }

    private Endpoint createEndPoint(String link, String description, String responseExample){
        //Endpoint p = new Endpoint("POST http://marcosarruda.info/taxis/drivers",
         //       "This endpoint creates/registers a new Taxi Driver. You will get 200 HTTP response if everything went fine.");
        Endpoint p = new Endpoint(link, description);
        p.setResponseExample(responseExample);
        return p;
    }
}
