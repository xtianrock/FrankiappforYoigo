package com.appcloud.frankiappforyoigo.POJO;

/**
 * Created by cristian on 19/07/2016.
 */
public class User {

    private String email;
    private boolean administrador;

    public User(String email) {
        this.email = email;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean admiistrador) {
        this.administrador = admiistrador;
    }
}
