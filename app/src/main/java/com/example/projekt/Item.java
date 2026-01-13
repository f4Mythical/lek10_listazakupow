package com.example.projekt;

public class Item {
    private String name;
    private Boolean czyWykonane; // do pokreslen idk
    private byte priorytet;

    public Item(String name, byte priorytet) {
        this.name = name;
        this.czyWykonane = false;
        this.priorytet = priorytet;
    }

    public Item(String name) {
        this(name, (byte)1);
    }

    public byte getPriorytet() {
        return priorytet;
    }

    public void setPriorytet(byte priorytet) {
        this.priorytet = priorytet;
    }

    public Boolean getCzyWykonane() {
        return czyWykonane;
    }

    public void setCzyWykonane(Boolean czyWykonane) {
        this.czyWykonane = czyWykonane;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}