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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class DaftarActivity extends AppCompatActivity {
    Button btnKembali;
    EditText etnama;
    EditText etemail;
    EditText etpass;
    RadioGroup radioRole;
    RadioButton radioPenjual,radioPembeli;
    Button btnRegister;
    Context mContext;
    UserService userService;
    UserService mApiService;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        userService = ApiUtils.getUserService();
        mContext = this;
        mApiService = ApiUtils.getUserService();
        radioRole = (RadioGroup) findViewById(R.id.radioRole);
        etnama = (EditText)findViewById(R.id.etnama);
        etemail = (EditText)findViewById(R.id.etemail) ;
        etpass = (EditText)findViewById(R.id.etpassword);
        radioPenjual = (RadioButton)findViewById(R.id.radioPenjual);
        radioPembeli = (RadioButton)findViewById(R.id.radioPembeli);
        btnKembali = (Button) findViewById(R.id.btnKembali);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        /*btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });*/

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etnama.getText().toString().length()==0) {
                    etnama.setError("NAMA TIDAK BOLEH KOSONG!");
                }else if(etemail.getText().toString().length()==0) {
                    etemail.setError("EMAIL TIDAK BOLEH KOSONG!");
                }else if(etpass.getText().toString().length() ==0) {
                    etpass.setError("PASSWORD TIDAK BOLEH KOSONG!");
                }else {
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    requestRegister();
                }
            }
        });
    }

    public void requestRegister(){
        String role = "";
        String nama = etnama.getText().toString();
        String email = etemail.getText().toString();
        String pass = etpass.getText().toString();

        if(radioPenjual.isChecked()){
            role = "penjual";
        }else if(radioPembeli.isChecked()){
            role = "pembeli";
        }

        mApiService.registerRequest(nama,
                email,
                pass,
                role)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, LoginActivity.class));
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
