package com.galvanize.autosapi;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AutosApiApplicationTests {
  @Autowired
  TestRestTemplate restTemplate;

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
        auto = new Auto("blue", "subaru", "crosstrek", 2020, "abc"+(i + 12), "liz");
        auto.setColor(colors[r.nextInt(10)]);
      }
    }

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
    ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos", AutosList.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().isEmpty()).isFalse();
  }

  @Test
  void getAutos_search_returnsAutosList() {
    int seq = r.nextInt(50);
    String color = testAutos.get
    ResponseEntity<AutosList> response = restTemplate.getForEntity("/api/autos", AutosList.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().isEmpty()).isFalse();
  }

}
