package com.mobile.dental.model;

import java.io.Serializable;

public class UpdateProfileResult implements Serializable {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
