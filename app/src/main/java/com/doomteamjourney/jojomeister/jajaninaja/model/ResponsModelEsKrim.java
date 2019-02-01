package com.doomteamjourney.jojomeister.jajaninaja.model;

import java.util.List;

public class ResponsModelEsKrim {
    String  kode, pesan;
    List<ModelEsKrim> result;

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

    public List<ModelEsKrim> getResult() {
        return result;
    }

    public void setResult(List<ModelEsKrim> result) {
        this.result = result;
    }
}
