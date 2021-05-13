package com.galvanize.autosapi;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AutosController.class)
public class AutosControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockBean
  AutosService autosService;

  ArrayList<Auto> autosList;

  @BeforeEach
  void setUp() {
    autosList = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      autosList.add(new Auto("silver", "honda", "civic", 2016, "abc123" + i, "bob"));
    }
  }
    public String toJSON(Object auto) throws JsonProcessingException {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(auto);

    }

  //GETALL
// GET: /api/autos returns list of all autos in database
  @Test
  void getRequest_noParams_SuccessfullyReturnsAllAutos() throws Exception {
    setUp();
    when(autosService.getAutos()).thenReturn(new AutosList(autosList));

    mockMvc.perform(get("/api/autos"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.autos", hasSize(5)));
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
    when(autosService.getAutos(anyString(),anyString())).thenReturn(new AutosList(autosList));

    mockMvc.perform(get("/api/autos?color=red&make=Honda"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.autos", hasSize(5)));
  }

  // GET
// GET: /api/autos/{vin} returns specific car with the VIN number specified
@Test
  public void getRequest_vinParam_ReturnsSingleCar() throws Exception{
    Auto auto = autosList.get(0);
    when(autosService.getAutoByVin(anyString())).thenReturn(auto);

    mockMvc.perform(get("/api/autos/" + auto.getVin()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("vin").value(auto.getVin()));
}

// GET: /api/autos/{vin} returns NoContent 204 error
@Test
void getRequest_vinParam_SuccessfullyReturns204Code() throws Exception {
  when(autosService.getAutoByVin(anyString())).thenReturn(null);

  mockMvc.perform(get("/api/autos/mvk342"))
          .andDo(print())
          .andExpect(status().isNoContent());
}

  //POST
// POST: /api/autos returns a newly created car
// POST: /api/autos?make=Ford returns a newly created Ford car
// POST: /api/autos?color=BLUE returns a newly created blue car
// POST: /api/autos?make=Ford&color=GOLD returns a newly created gold Ford car
@Test
  void postRequest_Params_SuccessfullyPostsCar() throws Exception {
    Auto auto = autosList.get(0);
    when(autosService.addAuto(any(Auto.class))).thenReturn(auto);

    mockMvc.perform(post("/api/autos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toJSON(auto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("vin").value(auto.getVin()));
}

// POST: /api/autos returns a 400 error if there is a bad request
@Test
void postRequest_Params_ReturnsErrorWithBadRequest() throws Exception {
  when(autosService.addAuto(any(Auto.class))).thenThrow(InvalidAutoException.class);

  mockMvc.perform(post("/api/autos")
      .contentType(MediaType.APPLICATION_JSON)
      .content(toJSON(autosList.get(0))))
      .andExpect(status().isBadRequest());
}

  //PATCH
// PATCH: /api/autos/{vin} updates and returns the specified vehicle
  @Test
  void patchRequest_params_returnsPatchedAuto() throws Exception {
    Auto auto = new Auto("silver", "honda", "civic", 2016, "abc1230", "bob");
    auto.setColor("purple");
    auto.setOwner("bob");
    when(autosService.getAutoByVin(anyString())).thenReturn(auto);
    when(autosService.saveAuto(any(Auto.class))).thenReturn(auto);

    mockMvc.perform(patch("/api/autos/" + auto.getVin())
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"color\":\"purple\",\"owner\":\"rob\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("color").value("purple"))
            .andExpect(jsonPath("owner").value("rob"));
  }

// PATCH: /api/autos/{vin} returns a 204 error if vehicle is not found
// PATCH: /api/autos/{vin} returns a 400 error if there is a bad request
  @Test
  void patchRequest_vinParam_SuccessfullyReturnsBadRequest() throws Exception {
    when(autosService.getAutoByVin(anyString())).thenReturn(null);

    mockMvc.perform(patch("/api/autos/mvk342")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"color\":\"purple\",\"owner\":\"bob\"}"))
            .andDo(print())
            .andExpect(status().isNoContent());
  }

  //DELETE
// DELETE: /api/autos/{vin} Returns 202 response if delete request accepted
  @Test
  void deleteAuto_withVin_returns202() throws Exception {
    Auto auto = autosList.get(0);
    when(autosService.getAutoByVin(anyString())).thenReturn(auto);

    mockMvc.perform(delete("/api/autos/" + auto.getVin()))
        .andExpect(status().isAccepted());

    verify(autosService).deleteAuto(anyString());
  }

// DELETE: /api/autos/{vin} Returns 204 error if vehicle is not found
@Test
void deleteAuto_withVin_returnsVehicleNotFound() throws Exception {
  when(autosService.getAutoByVin(anyString())).thenReturn(null);

  mockMvc.perform(get("/api/autos/mvk342"))
      .andDo(print())
      .andExpect(status().isNoContent());
}


// DELETE: /api/autos/{vin} Returns 400 error if there is a bad request



}
