package com.doomteamjourney.jojomeister.jajaninaja.model;

import java.util.List;

public class ResponsModelProfilPelanggan {
    String id_pelanggan,nama_pelanggan,no_telp;
    List<ModelProfilPelanggan> result;

    public String getId_pelanggan(){return id_pelanggan;}

    public void setId_pelanggan(String id_pelanggan) { this.id_pelanggan = id_pelanggan; }

    public String getNama_pelanggan(){return nama_pelanggan;}

    public void setNama_pelanggan(String nama_pelanggan) { this.nama_pelanggan = nama_pelanggan; }

    public String getNo_telp() { return no_telp; }

    public void setNo_telp(String no_telp) { this.no_telp = no_telp; }

    public List<ModelProfilPelanggan> getResult() { return result; }

    public void setResult(List<ModelProfilPelanggan> result) { this.result = result; }

}
