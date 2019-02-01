package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doomteamjourney.jojomeister.jajaninaja.R;
import com.doomteamjourney.jojomeister.jajaninaja.remote.SharedPrefManager;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.ApiUtils;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilPelangganUpdateActivity extends AppCompatActivity {
    UserService userService;
    UserService mApiService;
    ProgressDialog loading;
    Context mContext;
    EditText etnama,etemail,etid;
    EditText etnotelp;
    Button btnSimpan;
    String gemail,nama,id,notelp;
    com.doomteamjourney.jojomeister.jajaninaja.remote.SharedPrefManager SharedPrefManager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_update);
        SharedPrefManager = new SharedPrefManager(this);
        mApiService = ApiUtils.getUserService();
        mContext = this;


        btnSimpan = (Button) findViewById(R.id.btnUpdate);
        //etnama = (EditText)findViewById(R.id.etnamaprofill);
        //etnotelp = (EditText)findViewById(R.id.etnotelpp);
        etemail = (EditText)findViewById(R.id.etemaill);
        etnama = (EditText)findViewById(R.id.etnamaprofill);
        etid = (EditText)findViewById(R.id.etidd);
        etnotelp = (EditText)findViewById(R.id.etnotelpp);

        gemail = SharedPrefManager.getSPEmail();
        etemail.setText(gemail);
        nama = SharedPrefManager.getSpNamaPelanggan();
        etnama.setText(nama);
        id = SharedPrefManager.getSpIdPelanggan();
        etid.setText(id);
        notelp = SharedPrefManager.getSpNoTelp();
        etnotelp.setText(notelp);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                String nama_pelanggan = etnama.getText().toString();
                String no_telp        = etnotelp.getText().toString();

                SharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA_PELANGGAN, nama_pelanggan);
                SharedPrefManager.saveSPString(SharedPrefManager.SP_NO_TELP, no_telp);

                requestProfilPelangganUpdate();
                
            }
        });
    }
    public void requestProfilPelangganUpdate(){
        String nama_pelanggan = etnama.getText().toString();
        String no_telp = etnotelp.getText().toString();
        String email = gemail;
        mApiService.updatePelanggan(email,nama_pelanggan,no_telp)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "BERHASIL UPDATE DATA", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ProfilPelangganUpdateActivity.this, ProfilPelangganActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: GA BERHASIL");
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
