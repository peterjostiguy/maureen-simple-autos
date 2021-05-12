package com.galvanize.autosapi;

//import static org.junit.jupiter.api.AssertFalse.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
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
  void getAutosBySearch() {
    Auto auto = new Auto("silver", "honda", "civic", 2016, "abc1230", "bob");
    auto.setColor("red");
    when(autosRepository.findByColorAndMake(anyString(),anyString())).thenReturn(Arrays.asList(auto));
    AutosList autosList = autosService.getAutos("red", "honda");
    assertNotNull(autosList);
    assertNotEquals(0, autosList.getAutos().size());
  }

  @Test
  void getAutoByVin() {
    Auto auto = new Auto("silver", "honda", "civic", 2016, "abc1230", "bob");
    when(autosRepository.findByVin(anyString())).thenReturn(auto);
    Auto actual = autosService.getAutoByVin("abc1230");
    assertNotNull(actual);
    assertEquals("honda", actual.getMake());

  }

  @Test
  void addAuto() {
    Auto auto = new Auto("silver", "honda", "civic", 2016, "abc1230", "bob");
    when(autosRepository.addAuto(any(Auto.class))).thenReturn(auto);
    Auto actual = autosService.addAuto(auto);
    assertNotNull(actual);
    assertEquals("honda", actual.getMake());


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
