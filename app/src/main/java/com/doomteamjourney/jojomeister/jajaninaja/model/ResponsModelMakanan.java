package com.doomteamjourney.jojomeister.jajaninaja.model;

import java.util.List;

public class ResponsModelMakanan {
    String  kode, pesan;
    List<ModelMakanan> result;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ModelMakanan> getResult() {
        return result;
    }

    public void setResult(List<ModelMakanan> result) {
        this.result = result;
    }
}
