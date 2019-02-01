package com.doomteamjourney.jojomeister.jajaninaja.model;

import java.util.List;

public class ResponsModelMinuman {
    String  kode, pesan;
    List<ModelMinuman> result;

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

    public List<ModelMinuman> getResult() {
        return result;
    }

    public void setResult(List<ModelMinuman> result) {
        this.result = result;
    }
}
