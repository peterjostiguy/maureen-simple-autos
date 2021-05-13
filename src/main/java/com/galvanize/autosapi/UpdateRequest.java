package com.galvanize.autosapi;

public class UpdateRequest {
    private String color;
    private String owner;

    public UpdateRequest(String color, String owner) {
        this.color = color;
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getColor() {
        return color;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}
