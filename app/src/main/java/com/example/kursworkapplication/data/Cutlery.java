package com.example.kursworkapplication.data;

public class Cutlery {
    private Long id;
    private int count;
    private String name;
    private String userLogin;

    @Override
    public String toString(){
        return String.format("Cutlery = {Id = %s, count = %d, name = %s}",
                id.toString(), count, name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }
}
