package com.doomteamjourney.jojomeister.jajaninaja.model;

import java.util.List;

public class ResponsModelCart {
    String kode, pesan;
    List<ModelCart> result;

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

    public List<ModelCart> getResult() {
        return result;
    }

    public void setResult(List<ModelCart> result) {
        this.result = result;
    }
}
