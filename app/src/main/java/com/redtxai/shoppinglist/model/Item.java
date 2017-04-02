package com.redtxai.shoppinglist.model;

/**
 * Created by redtx on 19/03/2017.
 * redtxai@gmail.com
 */

public class Item {
    private Integer id;
    private String name;
    private String brand;
    private Integer amount;

    public Item () {}

    public Item (String name, String brand, Integer amount) {
        this.id = -1;
        this.name = name;
        this.brand = brand;
        this.amount = amount;
    }

    public Item (Integer id, String name, String brand, Integer amount) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String toSaveString() {
        return this.id + "~" + this.name + "~" + this.brand + "~" + this.amount.toString();
    }

    @Override
    public String toString() {
        return this.name + " - " + this.brand + ": " + this.amount.toString();
    }
}
