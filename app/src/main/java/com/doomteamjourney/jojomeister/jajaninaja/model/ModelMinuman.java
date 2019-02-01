package com.doomteamjourney.jojomeister.jajaninaja.model;

import com.google.gson.annotations.SerializedName;

public class ModelMinuman {
    @SerializedName("id_menu")
    private String id_menu;
    @SerializedName("id_penjual")
    private String id_penjual;
    @SerializedName("nama_menu")
    private String nama_menu;
    @SerializedName("harga_menu")
    private int harga_menu;
    @SerializedName("jenis_menu")
    private String jenis_menu;
    @SerializedName("foto")
    private String foto;
    @SerializedName("stand")
    private String stand;

    public ModelMinuman(String id_menu, String id_penjual, String nama_menu, int harga_menu, String jenis_menu, String foto, String stand) {
        this.id_menu = id_menu;
        this.id_penjual = id_penjual;
        this.nama_menu = nama_menu;
        this.harga_menu = harga_menu;
        this.jenis_menu = jenis_menu;
        this.foto = foto;
        this.stand = stand;
    }

    public String getId_menu() {
        return id_menu;
    }

    public void setId_menu(String id_menu) {
        this.id_menu = id_menu;
    }

    public String getId_penjual() {
        return id_penjual;
    }

    public void setId_penjual(String id_penjual) {
        this.id_penjual = id_penjual;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public int getHarga_menu() {
        return harga_menu;
    }

    public void setHarga_menu(int harga_menu) {
        this.harga_menu = harga_menu;
    }

    public String getJenis_menu() {
        return jenis_menu;
    }

    public void setJenis_menu(String jenis_menu) {
        this.jenis_menu = jenis_menu;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }
}
