package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.doomteamjourney.jojomeister.jajaninaja.R;

public class WelcomeActivity extends AppCompatActivity {

    private int waktu_loading=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent login = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(login);
                finish();

            }
        },waktu_loading);
    }
}
