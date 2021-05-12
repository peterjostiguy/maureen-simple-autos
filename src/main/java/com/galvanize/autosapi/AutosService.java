package com.galvanize.autosapi;


import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class AutosService {

    AutosRepository autosRepository;

    public AutosService(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    public AutosList getAutos() {
        return new AutosList(autosRepository.findAll());
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

    public Auto saveAuto(Auto auto) {
        return null;
    }

    public ArrayList<Auto> deleteAuto(String vin) {
        return null;
    }

}
