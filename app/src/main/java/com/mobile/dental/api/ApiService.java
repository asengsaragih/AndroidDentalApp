package com.mobile.dental.api;

import com.mobile.dental.model.Auth;
import com.mobile.dental.model.Bot;
import com.mobile.dental.model.Doctor;
import com.mobile.dental.model.PasientResponse;
import com.mobile.dental.model.Profile;
import com.mobile.dental.model.Register;
import com.mobile.dental.model.UpdateProfileResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    //untuk login
    @FormUrlEncoded
    @POST("index.php/api/User_Klinik/login")
    Call<Auth> login(
            @Field("username") String username,
            @Field("pass") String password
    );

    //untuk register
    @FormUrlEncoded
    @POST("index.php/api/User_Klinik/register")
    Call<Register> register(
            @Field("username") String username,
            @Field("pass") String pass,
            @Field("email") String email,
            @Field("contact") String contact,
            @Field("fullname") String fullname
    );

    //untuk form pendaftaran
    @FormUrlEncoded
    @POST("index.php/api/User_Klinik/pasien")
    Call<PasientResponse> postPasient(
            @Field("nama_pasient") String namaPasien,
            @Field("gender") String gender,
            @Field("umur") String umur,
            @Field("gol_darah") String golDarah,
            @Field("keluhan") String keluhan,
            @Field("tanggal_pendaftaran") String tanggalPendaftaran,
            @Field("waktu_pelayanan") String waktuPelayanan,
            @Field("tanggal_pelayanan") String tanggalPelayanan,
            @Field("id_user") String idUser,
            @Field("id_dokter") String idDokter,
            @Field("id_status") String idStatus
    );

    @GET("index.php/api/User_Klinik/getAllDokter")
    Call<List<Doctor>> getAllDoctor();

    @GET("index.php/api/User_Klinik/profile/{idUser}")
    Call<List<Profile>> getUser(@Path("idUser") String code);

    //update profile
    @FormUrlEncoded
    @POST("index.php/api/User_Klinik/putProfile")
    Call<UpdateProfileResult> updateProfile(
            @Field("id") String id,
            @Field("fullname") String fullname,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("kontak") String kontak
    );

    @GET("https://dental-clinic-telu.000webhostapp.com/index.php/api/User_Klinik/chatbot/0/0")
    Call<Bot> getInitialBot();

    @GET("https://dental-clinic-telu.000webhostapp.com/index.php/api/User_Klinik/chatbot/{idLayanan}/0")
    Call<List<Bot.Chatbot>> chooseLayanan(@Path("idLayanan") String code);

    @GET("https://dental-clinic-telu.000webhostapp.com/index.php/api/User_Klinik/chatbot/{idLayanan}/{idResult}")
    Call<List<Bot.Chatbot>> resultDeskripsi(@Path("idLayanan") String code, @Path("idResult") String idResult);
}

