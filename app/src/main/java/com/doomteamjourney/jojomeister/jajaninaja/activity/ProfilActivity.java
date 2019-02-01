package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.doomteamjourney.jojomeister.jajaninaja.R;
import com.doomteamjourney.jojomeister.jajaninaja.remote.SharedPrefManager;

public class ProfilActivity extends AppCompatActivity {
    TextView txtPengaturan;
    SharedPrefManager sharedPrefManager;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        sharedPrefManager = new SharedPrefManager(this);

        txtPengaturan = (TextView) findViewById(R.id.txtPengaturan);
        email = sharedPrefManager.getSPEmail();

        txtPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilActivity.this,ChangePasswordActivity.class);
                Bundle b = new Bundle();
                b.putString("parse_email", email);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}
