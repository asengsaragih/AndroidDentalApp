package com.mobile.dental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Doctor implements Serializable {
    @SerializedName("id_dokter")
    private String id;

    @SerializedName("nama_dokter")
    private String name;

    @SerializedName("umur")
    private String age;

    private String gender;
    private List<String> jamPraktik;
    private List<String> hariKerja;
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

    public List<String> getJamPraktik() {
        return jamPraktik;
    }

    public List<String> getHariKerja() {
        return hariKerja;
    }

    public String getStatus() {
        return status;
    }
}