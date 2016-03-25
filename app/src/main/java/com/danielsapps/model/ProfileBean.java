package com.danielsapps.model;

import java.io.Serializable;

/**
 * Created by daniel on 3/20/16.
 */
public class ProfileBean implements Serializable{

    String name;
    String email;
    String homeCity;
    String password;
    String img;

    public ProfileBean(String name, String email, String homeCity, String password, String img){
        this.name = name;
        this.email=email;
        this.homeCity=homeCity;
        this.password=password;
        this.img = img;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public String getPassword() {
        return password;
    }

    public String getImg() {
        return img;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
