package com.mobile.dental.api;

import com.mobile.dental.model.Auth;
import com.mobile.dental.model.Doctor;
import com.mobile.dental.model.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    //login
    @FormUrlEncoded
    @POST("index.php/api/User_Klinik/login")
    Call<Auth> login(
            @Field("username") String username,
            @Field("pass") String password
    );

    //register
    @FormUrlEncoded
    @POST("index.php/api/User_Klinik/register")
    Call<Register> register(
            @Field("username") String username,
            @Field("pass") String password,
            @Field("email") String email,
            @Field("contact") String contact
    );

    @GET("index.php/api/User_Klinik/getAllDokter")
    Call<List<Doctor>> getAllDoctor();

}
