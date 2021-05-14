package com.galvanize.autosapi;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
@Table(name = "autos")
public class Auto {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String color;
  @Column(length = 50)
  private String make;
  private String model;
  @Column(name = "model_year")
  private int year;
  @Column(unique = true)
  private String vin;
  @Column(name = "owner_name")
  private String owner;
  @JsonFormat(pattern = "MM/dd/yyyy")
  private Date purchaseDate;

  public Auto() {}

  public Auto(String color, String make, String model, int year, String vin,
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

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
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

  public Date getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(Date purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  @Override
  public String toString() {
    return "Auto{" +
        "color='" + color + '\'' +
        ", make='" + make + '\'' +
        ", model='" + model + '\'' +
        ", year=" + year +
        ", vin='" + vin + '\'' +
        ", owner='" + owner + '\'' +
        '}';
  }
}
