package com.galvanize.autosapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations= "classpath:application-test.properties")
class AutosApiApplicationTests {
  @Autowired
  TestRestTemplate testRestTemplate;
  RestTemplate restTemplate;

  @Autowired
  AutosRepository autosRepository;

  Random r = new Random();
  List<Auto> testAutos;

  @BeforeEach
  void setUp() {
    this.testAutos = new ArrayList<>();
    Auto auto;
    String[] colors = {"red", "green", "blue", "yellow", "orange", "black", "gold", "silver", "purple", "pink"};

    for (int i = 0; i < 50; i++) {
      if (i % 3 == 0){
        auto = new Auto("blue", "honda", "civic", 1991, "abc"+(i + 13), "bob");
        auto.setColor(colors[r.nextInt(10)]);
      } else  if (i % 2 == 0){
        auto = new Auto("blue", "toyota", "camry", 2003, "def"+(i + 12), "carol");
        auto.setColor(colors[r.nextInt(10)]);
      } else {
        auto = new Auto("blue", "subaru", "crosstrek", 2020, "ghi"+(i + 12), "liz");
        auto.setColor(colors[r.nextInt(10)]);
      }
      this.testAutos.add(auto);
    }
    autosRepository.saveAll(this.testAutos);

  }

  @AfterEach
  void tearDown(){
    autosRepository.deleteAll();
  }

  @Test
  void contextLoads() {
  }

  @Test
  void getAutos_exists_returnsAutosList() {
    ResponseEntity<AutosList> response = testRestTemplate.getForEntity("/api/autos", AutosList.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().isEmpty()).isFalse();
    for (Auto auto : response.getBody().getAutos()) {
        System.out.println(auto);
    }
  }

  @Test
  void getAutos_search_returnsAutosList() {
    int seq = r.nextInt(50);
    String color = testAutos.get(seq).getColor();
    String make = testAutos.get(seq).getMake();
    ResponseEntity<AutosList> response = testRestTemplate.getForEntity(
            String.format("/api/autos?color=%s&make=%s", color, make), AutosList.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().isEmpty()).isFalse();
    assertThat(response.getBody().getAutos().size()).isGreaterThanOrEqualTo(1);
      for (Auto auto : response.getBody().getAutos()) {
          System.out.println(auto);
      }
  }

  @Test
  void addAuto_returnsNewAutoDetails() {
    Auto auto = new Auto("silver", "honda", "civic", 2016, "abc1230", "bob");

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<Auto> request = new HttpEntity<>(auto, headers);

    ResponseEntity<Auto> response = testRestTemplate.postForEntity("/api/autos", request, Auto.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getVin()).isEqualTo(auto.getVin());
  }

//  private TestRestTemplate restTemplate;

  @Test
  void updateAutoWithPatch() throws Throwable {
    Auto testAuto = testAutos.get(0);
    this.restTemplate = testRestTemplate.getRestTemplate();
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    String uri = "/api/autos/"+testAuto.getVin();
    String updateBody = toJSON(new UpdateRequest("grey", "jim"));
    ResponseEntity<Auto> responseEntity = restTemplate.exchange(
            uri, HttpMethod.PATCH, getPostRequestHeaders(updateBody), Auto.class);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
    Auto returnedAuto = responseEntity.getBody();
    assertNotNull(returnedAuto);
    assertEquals("grey", returnedAuto.getColor());
    assertEquals("jim", returnedAuto.getOwner());
  }
  public String toJSON(Object auto) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(auto);
  }
  public HttpEntity getPostRequestHeaders(String body) {
    List acceptTypes = new ArrayList();
    acceptTypes.add(MediaType.APPLICATION_JSON);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(acceptTypes);
    return new HttpEntity(body, headers);
  }

//  @Test
//  void patchAuto_returnsUpdatedAutoDetails() {
//    testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
//
//    int random = r.nextInt(50);
//    Auto auto = testAutos.get(random);
//    String vin = auto.getVin();
//
//    UpdateRequest updateRequest = new UpdateRequest("grey","jim");
//    HttpHeaders headers = new HttpHeaders();
//    headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
//    HttpEntity<UpdateRequest> request = new HttpEntity<>(updateRequest, headers);
//    ResponseEntity<Auto> response =
//            patchRestTemplate.exchange("/api/autos"+ vin, HttpMethod.PATCH,
//                   request, Auto.class);
//
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    assertThat(response.getBody().getVin()).isEqualTo(auto.getVin());
//  }


  @Test
  void deleteAuto_returnsSuccessfulResponse() {

    int random = r.nextInt(50);
    Auto auto = testAutos.get(random);
    String vin = auto.getVin();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<Auto> request = new HttpEntity<>(auto, headers);

    ResponseEntity<Auto> response = testRestTemplate.getForEntity("/api/autos/" + vin, Auto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    testRestTemplate.delete("/api/autos/" + vin);
    ResponseEntity<Auto> response2 = testRestTemplate.getForEntity("/api/autos/" + vin, Auto.class);
    assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
  }
}
