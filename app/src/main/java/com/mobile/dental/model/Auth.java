package com.mobile.dental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Auth implements Serializable {
    private String status;
    private String message;

    @SerializedName("idUser")
    private String id;

    private String email;
    private String contact;
    private String fullname;

    public Auth(String status, String message, String id, String email, String contact, String fullname) {
        this.status = status;
        this.message = message;
        this.id = id;
        this.email = email;
        this.contact = contact;
        this.fullname = fullname;
    }

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

    public String getFullname() {
        return fullname;
    }
}
