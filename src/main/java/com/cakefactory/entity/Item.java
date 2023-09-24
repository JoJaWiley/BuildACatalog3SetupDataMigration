package com.cakefactory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    public Item() {

    }

    public Item(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
