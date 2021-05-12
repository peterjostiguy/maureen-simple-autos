package com.galvanize.autosapi;

public class Auto {

  private String color;
  private String make;
  private String model;
  private Integer year;
  private String vin;
  private String owner;

  public Auto() {}

  public Auto(String color, String make, String model, Integer year, String vin,
      String owner) {
    this.color = color;
    this.make = make;
    this.model = model;
    this.year = year;
    this.vin = vin;
    this.owner = owner;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getMake() {
    return make;
  }

  public void setMake(String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }
}
