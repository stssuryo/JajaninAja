package com.doomteamjourney.jojomeister.jajaninaja.model;

import com.google.gson.annotations.SerializedName;

public class ModelCart {
    @SerializedName("id_note")
    private String id_nota;
    @SerializedName("id_pelanggan")
    private String id_pelanggan;
    @SerializedName("id_penjual")
    private String id_penjual;
    @SerializedName("total_harga")
    private int total_harga;
    @SerializedName("stat")
    private String stat;
    @SerializedName("id_menu")
    private String id_menu;
    @SerializedName("jumlah_pesanan")
    private String jumlah_pesanan;
    @SerializedName("sub_tot_harga")
    private String sub_tot_harga;

    public ModelCart(String id_nota, String id_pelanggan, String id_penjual, int total_harga, String stat, String id_menu, String jumlah_pesanan, String sub_tot_harga) {
        this.id_nota = id_nota;
        this.id_pelanggan = id_pelanggan;
        this.id_penjual = id_penjual;
        this.total_harga = total_harga;
        this.stat = stat;
        this.id_menu = id_menu;
        this.jumlah_pesanan = jumlah_pesanan;
        this.sub_tot_harga = sub_tot_harga;
    }

    public String getId_nota() {
        return id_nota;
    }

    public void setId_nota(String id_nota) {
        this.id_nota = id_nota;
    }

    public String getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getId_penjual() {
        return id_penjual;
    }

    public void setId_penjual(String id_penjual) {
        this.id_penjual = id_penjual;
    }

    public int getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(int total_harga) {
        this.total_harga = total_harga;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getId_menu() {
        return id_menu;
    }

    public void setId_menu(String id_menu) {
        this.id_menu = id_menu;
    }

    public String getJumlah_pesanan() {
        return jumlah_pesanan;
    }

    public void setJumlah_pesanan(String jumlah_pesanan) {
        this.jumlah_pesanan = jumlah_pesanan;
    }

    public String getSub_tot_harga() {
        return sub_tot_harga;
    }

    public void setSub_tot_harga(String sub_tot_harga) {
        this.sub_tot_harga = sub_tot_harga;
    }
}
