package com.galvanize.autosapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutosController {

    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }

    @GetMapping("/api/autos")
    public ResponseEntity<AutosList> getAutos(@RequestParam(required = false) String color,
        @RequestParam(required = false) String make) {
        AutosList autosList;
        if(color == null && make == null) {
            autosList = autosService.getAutos();
        } else {
            autosList = autosService.getAutos(color, make);
        }
        return autosList.list.size() == 0 ? ResponseEntity.noContent().build() :
            ResponseEntity.ok(autosList);
    }

    @GetMapping("/api/autos/{vin}")
    public Auto getAutoByVin(@PathVariable String vin) {
        return autosService.getAutoByVin(vin);
    }





}
