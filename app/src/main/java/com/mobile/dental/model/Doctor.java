package com.mobile.dental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Doctor implements Serializable {
    @SerializedName("id_dokter")
    private String id;

    @SerializedName("nama_dokter")
    private String name;

    @SerializedName("umur")
    private String age;

    private String gender;
    private String jamPraktik;
    private String hariPraktikAwal;
    private String hariPraktikAkhir;
    private String status;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getJamPraktik() {
        return jamPraktik;
    }

    public String getHariPraktikAwal() {
        return hariPraktikAwal;
    }

    public String getHariPraktikAkhir() {
        return hariPraktikAkhir;
    }

    public String getStatus() {
        return status;
    }
}
