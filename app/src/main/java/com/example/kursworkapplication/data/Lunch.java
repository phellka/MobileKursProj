package com.example.kursworkapplication.data;

import java.time.LocalDateTime;

public class Lunch {
    private Long id;
    private int price;
    private int weight;
    private LocalDateTime date;
    private String uerLogin;

    @Override
    public String toString(){
        return String.format("Lunch = {Id = %s, price = %d, weight = %d, date = %s}",
                id.toString(), price, weight,  date.toString());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUerLogin() {
        return uerLogin;
    }

    public void setUerLogin(String uerLogin) {
        this.uerLogin = uerLogin;
    }
}
