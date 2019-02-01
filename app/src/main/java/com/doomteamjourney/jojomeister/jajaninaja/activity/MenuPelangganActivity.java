package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.doomteamjourney.jojomeister.jajaninaja.R;
import com.doomteamjourney.jojomeister.jajaninaja.remote.SharedPrefManager;

public class MenuPelangganActivity extends AppCompatActivity {

    ImageButton btnMakanan, btnMinuman, btnEsKrim, btnSnack, btnTambahan;
    Button btnHome, btnLokasi, btnCart;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pelanggan);

        btnMakanan = (ImageButton) findViewById(R.id.btnMakanan);
        btnMinuman = (ImageButton) findViewById(R.id.btnMinuman);
        btnEsKrim = (ImageButton) findViewById(R.id.btnEsKrim);
        btnSnack = (ImageButton) findViewById(R.id.btnSnack);
        // btnLokasi = (Button) findViewById(R.id.btnLokasi);
        btnHome = (Button) findViewById(R.id.btnHome);
        btnCart = (Button) findViewById(R.id.btnCart);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MenuPelangganActivity.this, HomePelangganActivity.class);
                startActivity(intent);
            }
        });

        /* btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MenuPelangganActivity.this, LokasiActivity.class);
                startActivity(intent);
            }
        }); */

        btnMakanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MenuPelangganActivity.this, TampilMenuMakananActivity.class);
                startActivity(intent);
            }
        });

        btnMinuman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MenuPelangganActivity.this, TampilMenuMinumanActivity.class);
                startActivity(intent);
            }
        });

        btnSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MenuPelangganActivity.this, TampilMenuSnackActivity.class);
                startActivity(intent);
            }
        });

        btnEsKrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MenuPelangganActivity.this, TampilMenuEsKrimActivity.class);
                startActivity(intent);
            }
        });

    }
}
