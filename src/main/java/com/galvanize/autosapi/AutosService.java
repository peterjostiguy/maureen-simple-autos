package com.galvanize.autosapi;


import java.util.List;

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
        List<Auto> autos = autosRepository.findByColorContainsAndMakeContains(color, make);
        return new AutosList(autos);

    }

    public Auto getAutoByVin(String vin) {
        return autosRepository.findByVin(vin);
    }

    public Auto addAuto(Auto auto) {
        return autosRepository.save(auto);
    }

    public Auto saveAuto(Auto auto) {
        return autosRepository.save(auto);
    }

    public void deleteAuto(String vin) {
        Auto auto = autosRepository.findByVin(vin);
        if (auto != null) {
            autosRepository.delete(auto);
        } else {
            throw new InvalidAutoException("Auto not found");
        }
    }

}
