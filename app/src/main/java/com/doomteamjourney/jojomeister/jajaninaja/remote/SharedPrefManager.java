package com.doomteamjourney.jojomeister.jajaninaja.remote;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SP_JAJANINAJA = "spJajaninAja";

    //public static final String SP_NAMA = "spNama";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_ROLE = "spRole";
    public static final String SP_ID_PELANGGAN = "spIdPelanggan" ;
    public static final String SP_NAMA_PELANGGAN = "spNamaPelanggan";
    public static final String SP_NO_TELP = "spNotelp";
    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_JAJANINAJA, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    /*public String getSPNama(){
        return sp.getString(SP_NAMA, "");
    }*/

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSPRole(){
        return sp.getString(SP_ROLE, "");
    }
    public String getSpIdPelanggan(){return sp.getString(SP_ID_PELANGGAN, "");}
    public String getSpNamaPelanggan(){return sp.getString(SP_NAMA_PELANGGAN,"");}
    public String getSpNoTelp(){return  sp.getString(SP_NO_TELP,"");}

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
