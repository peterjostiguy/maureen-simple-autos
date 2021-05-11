package com.galvanize.autosapi;

import java.util.ArrayList;
import java.util.Objects;

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
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AutosList autosList = (AutosList) o;
    return Objects.equals(list, autosList.list);
  }

  @Override
  public int hashCode() {
    return Objects.hash(list);
  }

  @Override
  public String toString(){
    return "AutosList{" + "autos=" + list + "}";
  }
}
