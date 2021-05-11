package com.galvanize.autosapi;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AutosController.class)
public class AutosControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  AutosService autosService;

  AutosList autosList;

  void setUp() {
    autosList = new AutosList();
    autosList.list.add(new Auto("Ford", "green"));
    autosList.list.add(new Auto("Honda", "red"));
    autosList.list.add(new Auto("Nissan", "gold"));

  }

  //GETALL
// GET: /api/autos returns list of all autos in database
  @Test
  void getRequest_noParams_SuccessfullyReturnsAllAutos() throws Exception {
    setUp();
    when(autosService.getAutos()).thenReturn(autosList);

    mockMvc.perform(get("/api/autos"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.list", hasSize(3)));
  }

// GET: /api/autos no autos in database returns 204 no content

  @Test
  void getRequest_noParams_SuccessfullyReturns204Code() throws Exception {
    when(autosService.getAutos()).thenReturn(new AutosList());

    mockMvc.perform(get("/api/autos"))
        .andDo(print())
        .andExpect(status().isNoContent());
  }

// GET: /api/autos?color=BLUE returns blue cars
// GET: /api/autos?make=Honda returns all Honda cars
// GET: /api/autos?make=Honda&color=SILVER returns all silver Honda cars
  @Test
  void getRequest_params_SuccessfullyReturnsCorrectCars() throws Exception {

    setUp();
    when(autosService.getAutos(anyString(),anyString())).thenReturn(new AutosList(autosList.list));

    mockMvc.perform(get("/api/autos?color=red&make=Honda"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.list", hasSize(3)));
  }

  // GET
// GET: /api/autos/{vin} returns specific car with the VIN number specified
@Test
  public void getRequest_vinParam_ReturnsSingleCar() throws Exception{
//    setUp();
    autosList = new AutosList();
    Auto auto1 = new Auto("abc123");
    autosList.list.add(auto1);
    autosList.list.add(new Auto("123abc"));

    when(autosService.getAutoByVin(anyString())).thenReturn(auto1);

    mockMvc.perform(get("/api/autos/" + auto1.getVin()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("vin").value("abc123"));
}

// GET: /api/autos/{vin} returns NoContent 204 error
@Test
void getRequest_vinParam_SuccessfullyReturns204Code() throws Exception {
  when(autosService.getAutoByVin(anyString())).thenReturn(new Auto());

  mockMvc.perform(get("/api/autos/mvk342"))
          .andDo(print())
          .andExpect(status().isNoContent());
}


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
