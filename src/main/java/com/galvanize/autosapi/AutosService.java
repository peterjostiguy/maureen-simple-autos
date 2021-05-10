package com.galvanize.autosapi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AutosService {

    protected ArrayList<Auto> autoList = new ArrayList<Auto>();

    public AutosService() {

    }

    public ArrayList<Auto> getAutos() {
        return autoList;
    };



}
