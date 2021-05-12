package com.galvanize.autosapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autos")
public class AutosController {

    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }

    @GetMapping
    public ResponseEntity<AutosList> getAutos(@RequestParam(required = false) String color,
        @RequestParam(required = false) String make) {
        AutosList autosList;
        if(color == null && make == null) {
            autosList = autosService.getAutos();
        } else {
            autosList = autosService.getAutos(color, make);
        }
        return autosList.autos.size() == 0 ? ResponseEntity.noContent().build() :
            ResponseEntity.ok(autosList);
    }

    @GetMapping("/{vin}")
    public ResponseEntity<Auto> getAutoByVin(@PathVariable String vin) {
        Auto auto = autosService.getAutoByVin(vin);
        return auto == null ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(auto);
    }

    @PostMapping
    public Auto addAuto(@RequestBody Auto auto) {
        return autosService.addAuto(auto);
    }

    @PatchMapping("/{vin}")
    public Auto updateAuto(@PathVariable String vin, @RequestBody UpdateRequest updateRequest) {

        Auto auto = autosService.updateAuto(vin, updateRequest.getColor(), updateRequest.getOwner());
        auto.setColor(updateRequest.getColor());
        auto.setOwner(updateRequest.getOwner());

        return auto;
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity deleteAuto(@PathVariable String vin) {
        return getAutoByVin(vin) == null ? ResponseEntity.noContent().build() : ResponseEntity.accepted().build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void InvalidAutoExceptionHandler(InvalidAutoException invalidAutoException) {

    }



}
