package com.galvanize.autosapi;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

@WebMvcTest(AutosController.class)
public class AutosControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  AutosService autosService;

//  ArrayList<Auto> autoList;

  @BeforeEach
  void setUp() {
    for (int i = 0; i < 5; i++) {
      autosService.autoList.add(new Auto());
    }
  }


  @Test
  void getRequest_noParams_SuccessfullyReturnsAllAutos() throws Exception {

    when(autosService.getAutos()).thenReturn(autosService.autoList);

    mockMvc.perform(get("/api/autos"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(5)));
  }
  //GETALL
// GET: /api/autos returns list of all autos in database
// GET: /api/autos no autos in database returns 204 no content
// GET: /api/autos?color=BLUE returns blue cars
// GET: /api/autos?make=Honda returns all Honda cars
// GET: /api/autos?make=Honda&color=SILVER returns all silver Honda cars

  // GET
// GET: /api/autos/{vin} returns specific car with the VIN number specified
// GET: /api/autos/{vin} returns NoContent 204 error

  //POST
// POST: /api/autos returns a newly created car
// POST: /api/autos?make=Ford returns a newly created Ford car
// POST: /api/autos?color=BLUE returns a newly created blue car
// POST: /api/autos?make=Ford&color=GOLD returns a newly created gold Ford car
// POST: /api/autos returns a 400 error if there is a bad request

  //PATCH
// PATCH: /api/autos/{vin} updates and returns the specified vehicle
// PATCH: /api/autos/{vin} returns a 204 error if vehicle is not found
// PATCH: /api/autos/{vin} returns a 400 error if there is a bad request


  //DELETE
// DELETE: /api/autos/{vin} Returns 202 response if delete request accepted
// DELETE: /api/autos/{vin} Returns 204 error if vehicle is not found
// DELETE: /api/autos/{vin} Returns 400 error if there is a bad request



}
