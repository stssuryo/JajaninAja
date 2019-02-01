package com.doomteamjourney.jojomeister.jajaninaja.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.doomteamjourney.jojomeister.jajaninaja.model.ModelEsKrim;

import java.util.List;

public class AdapterEskrim extends RecyclerView.Adapter<AdapterEskrim.HolderEsKrim> {
    private List<ModelEsKrim> listItem;
    private Context mContext;

    public AdapterEskrim(List<ModelEsKrim> listItem, Context mContext) {
        this.listItem = listItem;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HolderEsKrim onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu,parent,false);
        HolderEsKrim holderM = new HolderEsKrim(layout);
        return holderM;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderEsKrim holder, int position) {
        ModelEsKrim mk = listItem.get(position);
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

    public class HolderEsKrim extends RecyclerView.ViewHolder{
        public TextView txNamaMenu, txHarga;
        public ImageView gbrMenu;
        public ImageButton gbrPesan;
        public ModelEsKrim mk;

        public HolderEsKrim (View v){
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

                    mContext.startActivity(pesan);
                }
            });
        }
    }
}
