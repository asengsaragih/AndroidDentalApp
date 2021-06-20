package com.mobile.dental.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Dashboard implements Serializable {
    @SerializedName("id_pendaftaran")
    private String idPendaftaran;

    @SerializedName("nama_pasient")
    private String namaPasient;

    private String gender;
    private String umur;

    @SerializedName("gol_darah")
    private String gol_darah;

    private String keluhan;

    @SerializedName("tanggal_pendaftaran")
    private String tanggalPendaftaran;

    @SerializedName("waktu_pendaftaran")
    private String waktuPendaftaran;

    @SerializedName("waktu_pelayanan")
    private String waktuPelayanan;

    @SerializedName("tanggal_pelayanan")
    private String tanggalPelayanan;

    @SerializedName("id_user")
    private String idUser;

    @SerializedName("id_dokter")
    private String idDokter;

    @SerializedName("id_status")
    private String idStatus;

    public String getIdPendaftaran() {
        return idPendaftaran;
    }

    public String getNamaPasient() {
        return namaPasient;
    }

    public String getGender() {
        return gender;
    }

    public String getUmur() {
        return umur;
    }

    public String getGol_darah() {
        return gol_darah;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public String getTanggalPendaftaran() {
        return tanggalPendaftaran;
    }

    public String getWaktuPendaftaran() {
        return waktuPendaftaran;
    }

    public String getWaktuPelayanan() {
        return waktuPelayanan;
    }

    public String getTanggalPelayanan() {
        return tanggalPelayanan;
    }

    public String getIdUser() {
        return idUser;
    }

    public String getIdDokter() {
        return idDokter;
    }

    public String getIdStatus() {
        return idStatus;
    }
}
