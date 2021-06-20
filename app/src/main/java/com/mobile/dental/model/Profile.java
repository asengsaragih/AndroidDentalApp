package com.mobile.dental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Profile implements Serializable {
    @SerializedName("id_user")
    private String idUser;

    private String fullname;
    private String username;
    private String password;
    private String email;
    private String kontak;

    public Profile(String idUser, String fullname, String username, String password, String email, String kontak) {
        this.idUser = idUser;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.kontak = kontak;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getKontak() {
        return kontak;
    }
}
