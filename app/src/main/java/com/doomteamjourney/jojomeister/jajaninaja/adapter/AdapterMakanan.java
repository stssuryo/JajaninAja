package com.doomteamjourney.jojomeister.jajaninaja.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doomteamjourney.jojomeister.jajaninaja.R;
import com.doomteamjourney.jojomeister.jajaninaja.activity.PesanActivity;
import com.doomteamjourney.jojomeister.jajaninaja.model.ModelMakanan;

import java.util.List;

public class AdapterMakanan extends RecyclerView.Adapter<AdapterMakanan.HolderMakanan>{
    private List<ModelMakanan> listItem;
    private Context mContext;

    public AdapterMakanan(List<ModelMakanan> listItem, Context mContext) {
        this.listItem = listItem;
        this.mContext = mContext;
    }


    @Override
    public HolderMakanan onCreateViewHolder(ViewGroup parent, int i) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu,parent,false);
        HolderMakanan holderM = new HolderMakanan(layout);
        return holderM;
    }

    @Override
    public void onBindViewHolder(HolderMakanan holder, int position) {
        ModelMakanan mk = listItem.get(position);
        holder.txNamaMenu.setText(mk.getNama_menu());
        holder.txHarga.setText("Rp. " + mk.getHarga_menu());
        Glide.with(mContext)
                .load(listItem.get(position).getFoto())
                .into(holder.gbrMenu);
        //Glide.with(mcontext).load(mtm.getFoto()).thumbnail(0.5f).crossFade().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.gbrMenu);
        holder.mk = mk;
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class HolderMakanan extends RecyclerView.ViewHolder{
        public TextView txNamaMenu, txHarga;
        public ImageView gbrMenu;
        public ImageButton gbrPesan;
        public ModelMakanan mk;

        public HolderMakanan (View v){
            super(v);
            txNamaMenu = (TextView) v.findViewById(R.id.txNamaMenu);
            txHarga = (TextView) v.findViewById(R.id.txHarga);
            gbrMenu = (ImageView) v.findViewById(R.id.gbrMenu);
            gbrPesan = (ImageButton) v.findViewById(R.id.gbrPesan);

            gbrPesan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pesan = new Intent(mContext,PesanActivity.class);
                    pesan.putExtra("nama_menu",mk.getNama_menu());
                    pesan.putExtra("id_penjual",mk.getId_penjual());
                    pesan.putExtra("id_menu",mk.getId_menu());
                    pesan.putExtra("harga_menu",mk.getHarga_menu());
                    pesan.putExtra("foto",mk.getFoto());
                    pesan.putExtra("stand",mk.getStand());

                    Bundle b = new Bundle();
                    b.putInt("harga_menu",mk.getHarga_menu());
                    pesan.putExtras(b);

                    mContext.startActivity(pesan);
                }
            });
        }
    }
}