package com.galvanize.autosapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AutosServiceTest {

  @Mock
  AutosRepository autosRepository;

  AutosService autosService;
  List<Auto> testAutosList;

  @BeforeEach
  void setUp() {
    autosService = new AutosService(autosRepository);
    testAutosList = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      testAutosList.add(new Auto("silver", "honda", "civic", 2016, "abc123" + i, "bob"));
    }

  }

  @Test
  public void getAutos() {

    when(autosRepository.findAll()).thenReturn(testAutosList);
    AutosList actual = autosService.getAutos();

    assertEquals(testAutosList.size(), actual.getAutos().size());
  }

  @Test
  void testGetAutos() {
  }

  @Test
  void getAutoByVin() {
  }

  @Test
  void addAuto() {
  }

  @Test
  void saveAuto() {
  }

  @Test
  void deleteAuto() {
  }

//  @Test
//  public void getAutosByColorAndMake() {
//    Auto auto1 = new Auto("red", "toyota", "camry", 2016, "abc1239", "bob");
//    autosService.autosList.autos.add(auto1);
//    ArrayList<Auto> autos1 = new ArrayList<>();
//    autos1.add(auto1);
//
//    assertEquals(autos1.get(0), autosService.getAutos("red", "toyota").autos.get(0));
//  }

//  @Test
//  public void testGetAutoByVin() {
//
//    assertEquals(autosService.autosList.autos.get(3), autosService.getAutoByVin("abc1233"));
//
//  }


}
