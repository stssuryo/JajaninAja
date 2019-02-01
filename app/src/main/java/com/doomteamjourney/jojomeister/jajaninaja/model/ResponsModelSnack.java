package com.doomteamjourney.jojomeister.jajaninaja.model;

import java.util.List;

public class ResponsModelSnack {
    String  kode, pesan;
    List<ModelSnack> result;

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

    public List<ModelSnack> getResult() {
        return result;
    }

    public void setResult(List<ModelSnack> result) {
        this.result = result;
    }
}
