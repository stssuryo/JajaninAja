package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.doomteamjourney.jojomeister.jajaninaja.R;
import com.doomteamjourney.jojomeister.jajaninaja.model.ResponsModelProfilPelanggan;
import com.doomteamjourney.jojomeister.jajaninaja.remote.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfilPelangganActivity extends AppCompatActivity {
    @BindView(R.id.txKeluar)
    TextView txtPengaturan, txtKeluar;
    EditText etdmail,etnama,etid,etnotelp;
    com.doomteamjourney.jojomeister.jajaninaja.remote.SharedPrefManager SharedPrefManager;
    ResponsModelProfilPelanggan rs;
    String email,nama,id,notelp;
    ImageButton Edit;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        mContext = this;
        ButterKnife.bind(this);
        SharedPrefManager = new SharedPrefManager(this);

        Edit = (ImageButton) findViewById(R.id.btnEdit);
        etdmail = (EditText) findViewById(R.id.etdemail);
        etnama = (EditText) findViewById(R.id.namaprofil);
        etid = (EditText) findViewById(R.id.etid);
        etnotelp = (EditText)findViewById(R.id.etnotelp);
        txtPengaturan = (TextView) findViewById(R.id.txtPengaturan);
        txtKeluar = (TextView) findViewById(R.id.txKeluar);

        email = SharedPrefManager.getSPEmail();
        etdmail.setText(email);
        nama = SharedPrefManager.getSpNamaPelanggan();
        etnama.setText(nama);
        id = SharedPrefManager.getSpIdPelanggan();
        etid.setText(id);
        notelp = SharedPrefManager.getSpNoTelp();
        etnotelp.setText(notelp);
        txtPengaturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilPelangganActivity.this,ChangePasswordActivity.class);
                Bundle b = new Bundle();
                b.putString("parse_email", email);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        Edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(ProfilPelangganActivity.this,ProfilPelangganUpdateActivity.class);
                startActivity(intent);
            }
        });

        txtKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mematikan trigger session
                SharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                Toast.makeText(mContext, "BERHASIL LOGOUT", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProfilPelangganActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });
    }

}
