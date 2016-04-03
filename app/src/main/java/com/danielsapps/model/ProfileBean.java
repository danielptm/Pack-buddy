package com.danielsapps.model;

import com.google.gson.annotations.SerializedName;

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
    /**
     * Takes 4 String parameters and byte[] paramter. Representind the data of a new user.
     * @param name
     * @param email
     * @param homeCity
     * @param password
     * @param img
     */
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

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

}
