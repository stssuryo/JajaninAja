package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doomteamjourney.jojomeister.jajaninaja.R;
import com.doomteamjourney.jojomeister.jajaninaja.remote.SharedPrefManager;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.ApiUtils;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.UserService;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText txEmail;
    EditText txPassword;
    TextView txReset;
    ImageButton btnMasuk;
    ImageButton btnDaftar;
    UserService userService;
    Context mContext;
    UserService mApiService;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txEmail = (EditText) findViewById(R.id.txEmail);
        txPassword = (EditText) findViewById(R.id.txPassword);
        btnMasuk = (ImageButton) findViewById(R.id.btnMasuk);
        btnDaftar = (ImageButton) findViewById(R.id.btnDaftar);
        txReset = (TextView) findViewById(R.id.txReset);
        userService = ApiUtils.getUserService();

        mContext = this;
        mApiService = ApiUtils.getUserService(); // meng-init yang ada di package apihelper
        sharedPrefManager = new SharedPrefManager(this);


        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txEmail.getText().toString();
                String password = txPassword.getText().toString();
                if(validateLogin(email, password)){
                    loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                    //do login
                    doLogin(email, password);
                }
            }
        });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        txReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        if (sharedPrefManager.getSPSudahLogin()){
            if(sharedPrefManager.getSPRole()=="a") {
                startActivity(new Intent(LoginActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }else if(sharedPrefManager.getSPRole()=="p") {
                startActivity(new Intent(LoginActivity.this, HomePenjualActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }else if(sharedPrefManager.getSPRole()=="c") {
                startActivity(new Intent(LoginActivity.this, HomePelangganActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }else if(sharedPrefManager.getSPRole()=="sa"){
                startActivity(new Intent(LoginActivity.this, MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        }

    }

    private boolean validateLogin(String email, String password){
        if(email == null || email.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String email,final String password){
        Call call = userService.doLogin(email,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    loading.dismiss();
                    try {
                        JSONObject Obj = new JSONObject(response.body().string());
                        if (Obj.getString("error").equals("false")) {
                            String email = Obj.getString("email");
                            String role = Obj.getString("role");

                            sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, email);
                            sharedPrefManager.saveSPString(SharedPrefManager.SP_ROLE, role);
                            // Shared Pref ini berfungsi untuk menjadi trigger session login
                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            if(role.equals("a")){
                                Toast.makeText(mContext, "BERHASIL LOGIN ADMIN", Toast.LENGTH_SHORT).show();
                                //login start main activity
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("email", email);
                                startActivity(intent);
                                finish();
                            }else if(role.equals("p")){
                                Toast.makeText(mContext, "BERHASIL LOGIN PENJUAL", Toast.LENGTH_SHORT).show();
                                //login start main activity
                                Intent intent = new Intent(LoginActivity.this, HomePenjualActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("email", email);
                                startActivity(intent);
                                finish();
                            }else if(role.equals("c")){
                                String id_pelanggan = Obj.getString("id_pelanggan");
                                String nama_pelanggan = Obj.getString("nama_pelanggan");
                                String notelp = Obj.getString("no_telp");
                                // menyimpan email ke shared pref manager
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_ID_PELANGGAN, id_pelanggan);
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA_PELANGGAN, nama_pelanggan);
                                sharedPrefManager.saveSPString(SharedPrefManager.SP_NO_TELP, notelp);
                                Toast.makeText(mContext, "BERHASIL LOGIN PELANGGAN", Toast.LENGTH_SHORT).show();
                                //login start main activity
                                Intent intent = new Intent(LoginActivity.this, HomePelangganActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                Bundle b = new Bundle();
                                b.putString("parse_email", email);
                                intent.putExtras(b);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(mContext, "BERHASIL LOGIN SUPER ADMIN", Toast.LENGTH_SHORT).show();
                                //login start main activity
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("email", email);
                                startActivity(intent);
                                finish();
                            }

                        } else {
                            Toast.makeText(LoginActivity.this, "email atau password salah", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Tidak ada koneksi!", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Tidak ada koneksi! atau server bermasalah!", Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}
