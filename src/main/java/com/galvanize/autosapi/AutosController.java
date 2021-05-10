package com.galvanize.autosapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AutosController {

    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }

    @GetMapping("/api/autos")
    public ResponseEntity<ArrayList<Auto>> getAutos() {
        ArrayList<Auto> autoList = autosService.getAutos();
        return autoList.size() == 0 ? ResponseEntity.noContent().build() :
            ResponseEntity.ok(autoList);
    }




}
