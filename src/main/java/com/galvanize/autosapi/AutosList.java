package com.galvanize.autosapi;

import java.util.ArrayList;

public class AutosList {

  ArrayList<Auto> autos;

  public AutosList() {
    this.autos = new ArrayList<>();
  }

  public AutosList(ArrayList<Auto> autos) {
    this.autos = autos;
  }

  public ArrayList<Auto> getAutos() {
    return autos;
  }

  public void setAutos(ArrayList<Auto> autos) {
    this.autos = autos;
  }

  public boolean isEmpty() {
    return autos.isEmpty();
  }

  }
