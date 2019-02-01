package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.doomteamjourney.jojomeister.jajaninaja.R;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.ApiUtils;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    UserService userService;
    UserService mApiService;
    ProgressDialog loading;
    Context mContext;
    EditText etpassLama;
    EditText etpassBaru;
    ImageButton btnGantiPass;
    String get_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        userService = ApiUtils.getUserService();
        mContext = this;
        mApiService = ApiUtils.getUserService();
        etpassLama = (EditText) findViewById(R.id.etpassLama);
        etpassBaru = (EditText) findViewById(R.id.etpassBaru);
        btnGantiPass = (ImageButton) findViewById(R.id.btnGantiPass);

        Bundle b = getIntent().getExtras();
        get_email = b.getString("parse_email");

        btnGantiPass.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestChangePassword();
            }
        });
    }

    public void requestChangePassword(){
        String passLama = etpassLama.getText().toString();
        String passBaru = etpassBaru.getText().toString();
        String email = get_email;
        mApiService.resetRequest(email,passLama,passBaru)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "BERHASIL GANTI PASSWORD", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ChangePasswordActivity.this,ProfilActivity.class);
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
