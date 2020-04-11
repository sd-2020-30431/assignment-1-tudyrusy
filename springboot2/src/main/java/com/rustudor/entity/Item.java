package com.rustudor.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


@Entity
@Table
public class Item {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private int id;
    private String name;
    private int quantity;
    private int calories;
    @NotNull
    private Timestamp purchaseDate;
    @NotNull
    private Timestamp expirationDate;
    private Timestamp consumptionDate;
    @ManyToOne
    @NotNull
    private User userFK;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUserFK() {
        return userFK;
    }

    public Timestamp getConsumptionDate() {
        return consumptionDate;
    }

    public void setConsumptionDate(Timestamp consumptionDate) {
        this.consumptionDate = consumptionDate;
    }

    public void setUserFK(User userFK) {
        this.userFK = userFK;
    }

    public int days(){
        return ((int) ((this.getExpirationDate().getTime() - this.getPurchaseDate().getTime())/86400000));
    }
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", calories=" + calories +
                ", purchaseDate=" + purchaseDate +
                ", expirationDate=" + expirationDate +
                ", consumptionDate=" + consumptionDate +
                '}';
    }

    public int getPerDay() {
        return this.getQuantity()*this.getCalories()/this.days();
    }
}
