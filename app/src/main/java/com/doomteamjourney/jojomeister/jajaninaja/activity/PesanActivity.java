package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.doomteamjourney.jojomeister.jajaninaja.R;
import com.doomteamjourney.jojomeister.jajaninaja.remote.SharedPrefManager;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.ApiUtils;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.UserService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesanActivity extends AppCompatActivity {
    TextView namaMenu, namaMenu2, namaStand, subTotal;
    ImageButton btnKurang, btnTambah, btnPesan, btnHome;
    ImageView gbrMenu;
    EditText etjumlah;
    String strJumlah, strHarga;
    SharedPrefManager sharedPrefManager;
    UserService userService;
    Context mcontext;
    int harga, subtotal, jml;
    int jumlah = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        userService = ApiUtils.getUserService();
        sharedPrefManager = new SharedPrefManager(this);

        namaMenu = (TextView) findViewById(R.id.txNamaMenu);
        namaMenu2 = (TextView) findViewById(R.id.txNamaMenu2);
        namaStand = (TextView) findViewById(R.id.txStand);
        btnKurang = (ImageButton) findViewById(R.id.btnKurang);
        btnTambah = (ImageButton) findViewById(R.id.btnTambah);
        etjumlah = (EditText) findViewById(R.id.etJumlah);
        subTotal = (TextView) findViewById(R.id.txSubtotal);
        btnPesan = (ImageButton) findViewById(R.id.btnPesan);
        gbrMenu = (ImageView) findViewById(R.id.gbrMenu);
        btnHome = (ImageButton) findViewById(R.id.btnHome);



        Intent data = getIntent();
        Bundle b = getIntent().getExtras();

        namaMenu.setText(data.getStringExtra("nama_menu"));
        namaMenu2.setText(data.getStringExtra("nama_menu"));
        namaStand.setText(data.getStringExtra("stand"));
        etjumlah.setText("1");
        harga = b.getInt("harga_menu");
        subTotal.setText("Rp. " + String.valueOf(harga));
        //Glide.with(mcontext).load(data.getStringExtra("foto")).into(gbrMenu);


        if(etjumlah.getText().toString() == "1"){
            btnKurang.setEnabled(false);
            subTotal.setText("Rp. " + String.valueOf(harga));
        }else{
            btnKurang.setEnabled(true);
            btnTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    btnKurang.setEnabled(true);
                    jumlah++;
                    etjumlah.setText(String.valueOf(jumlah));
                    strJumlah = etjumlah.getText().toString();
                    jml = Integer.parseInt(strJumlah);
                    subtotal = harga * jml ;
                    subTotal.setText("Rp. " + String.valueOf(subtotal));
                }
            });
            btnKurang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(jumlah > 1) {
                        jumlah--;
                        etjumlah.setText(String.valueOf(jumlah));
                        strJumlah = etjumlah.getText().toString();
                        jml = Integer.parseInt(strJumlah);
                        subtotal = harga * jml ;
                        subTotal.setText("Rp. " + String.valueOf(subtotal));
                    }else{
                        btnKurang.setEnabled(false);
                    }
                }
            });
        }

       /* btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doPesan();
            }
        }); */

       btnHome.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(PesanActivity.this, HomePelangganActivity.class);
               startActivity(intent);
           }
       });

    }

    public void doPesan(){
        String id_pelanggan, id_penjual, id_menu, nama_menu, subtotal;
        Intent data = getIntent();

        id_pelanggan = sharedPrefManager.getSpIdPelanggan();
        id_penjual = data.getStringExtra("id_penjual");
        id_menu = data.getStringExtra("id_menu");

        userService.doPesan().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
