package com.galvanize.autosapi;

import org.springframework.stereotype.Component;


import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class AutosService {

    protected ArrayList<Auto> autoList = new ArrayList<Auto>();

    public AutosService() {

    }

    public ArrayList<Auto> getAutos() {
        return autoList;
    };



}
