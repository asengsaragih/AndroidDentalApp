package com.mobile.dental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class History implements Serializable {
    @SerializedName("id_pendaftaran")
    private String id;

    @SerializedName("nama_pasient")
    private String name;

    @SerializedName("nama_dokter")
    private String dokter;

    @SerializedName("tanggal_pelayanan")
    private String tanggal;

    @SerializedName("waktu_pelayanan")
    private String waktu;

    @SerializedName("hasil_pemeriksaan")
    private String hasil;

    private String saran;
    private String resep;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDokter() {
        return dokter;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public String getHasil() {
        return hasil;
    }

    public String getSaran() {
        return saran;
    }

    public String getResep() {
        return resep;
    }

    public String getTanggalWaktu(){
        return tanggal + " | " + waktu;
    }
}