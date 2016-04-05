package com.danielsapps.model;

/**
 * Created by daniel on 4/4/16.
 */
public class EmailAndHostel {

    String email;
    String hostel;
    int pageToLoad;

    public EmailAndHostel(String email, String hostel, int pageToLoad){
        this.email=email;
        this.hostel=hostel;
        this.pageToLoad=pageToLoad;
    }

    public EmailAndHostel(String email, String hostel){
        this.email=email;
        this.hostel=hostel;

    }

    public int getPageToLoad() {
        return pageToLoad;
    }

    public void setPageToLoad(int pageToLoad) {
        this.pageToLoad = pageToLoad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getEmail() {

        return email;
    }

    public String getHostel() {
        return hostel;
    }

}
