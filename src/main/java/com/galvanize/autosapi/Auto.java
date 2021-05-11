package com.galvanize.autosapi;

public class Auto {

  private String color;
  private String make;
//  private Integer year;
  private String vin;
  private String owner;

  public Auto() {

  }

  public Auto(String color, String make, String owner) {
    this.color = color;
    this.make = make;
    this.owner = owner;
//    this.year = year;
//    this.vin = vin;
  }

  public Auto(String vin) {
    this.vin = vin;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
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

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  //  @Override
//  public String toString() {return make + ", " + color;}

}
