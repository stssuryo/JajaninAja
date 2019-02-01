package com.doomteamjourney.jojomeister.jajaninaja.remote.api;

import com.doomteamjourney.jojomeister.jajaninaja.model.ResponsModelEsKrim;
import com.doomteamjourney.jojomeister.jajaninaja.model.ResponsModelMakanan;
import com.doomteamjourney.jojomeister.jajaninaja.model.ResponsModelMinuman;
import com.doomteamjourney.jojomeister.jajaninaja.model.ResponsModelProfilPelanggan;
import com.doomteamjourney.jojomeister.jajaninaja.model.ResponsModelSnack;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> doLogin(@Field("email") String email,
                               @Field("password") String password);
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(@Field("nama") String nama,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("role") String role);
    @FormUrlEncoded
    @POST("changepassword.php")
    Call<ResponseBody> resetRequest(@Field("email") String email,
                                    @Field("passLama") String passLama,
                                    @Field("passBaru") String passBaru);

    @GET("makanan.php")
    Call<ResponsModelMakanan> getMakanan();

    @GET("minuman.php")
    Call<ResponsModelMinuman> getMinuman();

    @GET("snack.php")
    Call<ResponsModelSnack> getSnack();

    @GET("eskrim.php")
    Call<ResponsModelEsKrim> getEsKrim();

    @FormUrlEncoded
    @POST("UpdatePelanggan.php")
    Call<ResponseBody> updatePelanggan(@Field("email") String email,
                                       @Field("nama_pelanggan") String nama_pelanggan,
                                       @Field("no_telp") String no_telp);
    @GET("profilpelanggan.php")
    Call<ResponsModelProfilPelanggan> getProfilPelanggan();

    @FormUrlEncoded
    @POST("pesan.php")
    Call<ResponseBody> doPesan();
}
