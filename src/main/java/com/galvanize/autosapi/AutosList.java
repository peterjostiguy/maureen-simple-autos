package com.galvanize.autosapi;

import java.util.ArrayList;

public class AutosList {

  ArrayList<Auto> list;

  public AutosList() {
    this.list = new ArrayList<>();
  }

  public AutosList(ArrayList<Auto> autosList) {
    this.list = autosList;
  }

  public ArrayList<Auto> getList() {
    return list;
  }

  public void setList(ArrayList<Auto> list) {
    this.list = list;
  }

  @Override
  public String toString(){
    return "AutosList{" + "autos=" + list + "}";
  }
}
