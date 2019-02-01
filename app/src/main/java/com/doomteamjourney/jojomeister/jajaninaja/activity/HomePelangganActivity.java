package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.doomteamjourney.jojomeister.jajaninaja.R;

public class HomePelangganActivity extends AppCompatActivity {
    Button btnMenu;
    Button btnLokasi;
    Button btnAkun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pelanggan);

        btnMenu = (Button) findViewById(R.id.btnMenu);
        //btnLokasi = (Button) findViewById(R.id.btnLokasi);
        btnAkun = (Button) findViewById(R.id.btnAkun);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePelangganActivity.this, MenuPelangganActivity.class);
                startActivity(intent);
            }
        });

        /* btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePelangganActivity.this, LokasiActivity.class);
                startActivity(intent);
            }
        }); */

        btnAkun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePelangganActivity.this, ProfilPelangganActivity.class);
                startActivity(intent);
            }
        });
    }
}
