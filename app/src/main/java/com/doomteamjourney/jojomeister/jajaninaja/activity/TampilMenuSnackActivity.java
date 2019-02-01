package com.doomteamjourney.jojomeister.jajaninaja.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.doomteamjourney.jojomeister.jajaninaja.R;
import com.doomteamjourney.jojomeister.jajaninaja.adapter.AdapterSnack;
import com.doomteamjourney.jojomeister.jajaninaja.model.ModelSnack;
import com.doomteamjourney.jojomeister.jajaninaja.model.ResponsModelSnack;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.ApiUtils;
import com.doomteamjourney.jojomeister.jajaninaja.remote.api.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilMenuSnackActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    List<ModelSnack> mItems = new ArrayList<>();
    ProgressDialog pd;
    Button btnHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_menu_snack);

        pd = new ProgressDialog(this);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();

        UserService api = ApiUtils.getUserService();
        Call<ResponsModelSnack> getdata = api.getSnack();
        getdata.enqueue(new Callback<ResponsModelSnack>() {
            @Override
            public void onResponse(Call<ResponsModelSnack> call, Response<ResponsModelSnack> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getKode());
                mItems = response.body().getResult();

                mAdapter = new AdapterSnack(mItems, TampilMenuSnackActivity.this);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponsModelSnack> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : respon gagal");
            }
        });

        btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilMenuSnackActivity.this, HomePelangganActivity.class);
                startActivity(intent);
            }
        });
    }
}
