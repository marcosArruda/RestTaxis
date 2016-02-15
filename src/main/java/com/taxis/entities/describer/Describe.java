package com.taxis.entities.describer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosarruda on 2/15/16.
 * This class describes all endpoints and their functionalities
 */
public class Describe {
    private String whoAmI = "Below you will find all endpoints available by my implementation with full description of " +
            "requestParams and responses. Enjoy!";
    private List<Endpoint> endpoints = new ArrayList<Endpoint>();

    public String getWhoAmI() {
        return whoAmI;
    }

    public void setWhoAmI(String whoAmI) {
        this.whoAmI = whoAmI;
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }
}
