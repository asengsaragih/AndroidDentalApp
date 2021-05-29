package com.mobile.dental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Auth implements Serializable {
    private String status;
    private String message;

    public Auth(String status, String message, String id, String email, String contact) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.email = email;
        this.contact = contact;
    }

    @SerializedName("idUser")
    private String id;

    private String email;
    private String contact;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }
}