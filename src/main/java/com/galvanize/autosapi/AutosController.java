package com.galvanize.autosapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Auto> getAutoByVin(@PathVariable String vin) {
        Auto auto = autosService.getAutoByVin(vin);
        return auto == null ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(auto);
    }

    @PostMapping("api/autos")
    public Auto addAuto(@RequestBody Auto auto) {
        return autosService.addAuto(auto);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void InvalidAutoExceptionHandler(InvalidAutoException invalidAutoException) {

    }




}
