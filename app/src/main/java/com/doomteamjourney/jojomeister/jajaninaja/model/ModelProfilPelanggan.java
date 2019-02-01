package com.doomteamjourney.jojomeister.jajaninaja.model;

import com.google.gson.annotations.SerializedName;

public class ModelProfilPelanggan {
    @SerializedName("id_pelanggan")
    private String id_pelanggan;
    @SerializedName("nama_pelanggan")
    private String nama_pelanggan;
    @SerializedName("no_telp")
    private String no_telp;

    public ModelProfilPelanggan(String id_pelanggan, String nama_pelanggan, String no_telp){
        this.id_pelanggan = id_pelanggan;
        this.nama_pelanggan = nama_pelanggan;
        this.no_telp = no_telp;

    }

    public String getId_pelanggan() { return id_pelanggan; }

    public void setId_pelanggan(String id_pelanggan) { this.id_pelanggan = id_pelanggan; }

    public String getNama_pelanggan() { return nama_pelanggan; }

    public void setNama_pelanggan(String nama_pelanggan) { this.nama_pelanggan = nama_pelanggan; }

    public String getNo_telp() { return no_telp; }

    public void setNo_telp(String no_telp) { this.no_telp = no_telp; }
}
