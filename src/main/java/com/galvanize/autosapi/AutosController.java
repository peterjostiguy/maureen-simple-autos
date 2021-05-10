package com.galvanize.autosapi;

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
    public ArrayList<Auto> getAutos() {
        return autosService.getAutos();
    }




}
