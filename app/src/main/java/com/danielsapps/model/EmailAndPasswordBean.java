package com.danielsapps.model;

/**
 * Created by daniel on 4/2/16.
 */
public class EmailAndPasswordBean {

    String email;
    String password;

    public EmailAndPasswordBean(String email, String password){
        this.email = email;
        this.password =password;

    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}