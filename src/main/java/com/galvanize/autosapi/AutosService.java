package com.galvanize.autosapi;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class AutosService {

//    protected ArrayList<Auto> autoList = new ArrayList<Auto>();
    public AutosList autoList;

    public AutosService() {

    }

    public AutosList getAutos() {
        return null;
    }

    public AutosList getAutos(String color, String make) {
        return null;
    }

    public Auto getAutoByVin(String vin) {
        return null;
    }

    public Auto addAuto(Auto auto) {
        return null;
    }

    public Auto updateAuto(String vin, String color, String owner) {
        return null;
    }
}
