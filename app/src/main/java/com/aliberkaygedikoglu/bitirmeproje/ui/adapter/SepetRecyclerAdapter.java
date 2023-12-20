package com.aliberkaygedikoglu.bitirmeproje.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteGetir;
import com.aliberkaygedikoglu.bitirmeproje.databinding.FragmentSepetBinding;
import com.aliberkaygedikoglu.bitirmeproje.databinding.SepetItemBinding;
import com.aliberkaygedikoglu.bitirmeproje.ui.viewmodel.SepetViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;
import java.util.List;



public class SepetRecyclerAdapter extends RecyclerView.Adapter<SepetRecyclerAdapter.ViewHolder> {
    private List<SepeteGetir> sepetYemeklerList;
    private Context mContext;

    private SepetViewModel viewModel;



    public SepetRecyclerAdapter(List<SepeteGetir> sepetYemeklerList, Context mContext, SepetViewModel viewModel) {
        this.sepetYemeklerList = sepetYemeklerList;
        this.mContext = mContext;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public SepetRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        SepetItemBinding binding = SepetItemBinding.inflate(LayoutInflater.from(mContext),parent,false);


        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SepetRecyclerAdapter.ViewHolder holder, int position) {



        SepeteGetir sepeteGetir = sepetYemeklerList.get(position);

        holder.binding.textViewSepetAd.setText(sepeteGetir.getYemek_adi());
        holder.binding.textViewSepetAdet.setText(sepeteGetir.getYemek_siparis_adet()+" Adet");
        holder.binding.textViewSepetFiyat.setText(sepeteGetir.getYemek_fiyat()+" ₺");


        holder.binding.textViewSepetTutar.setText(Integer.parseInt(sepeteGetir.getYemek_fiyat())*Integer.parseInt(sepeteGetir.getYemek_siparis_adet())+ " ₺");

        String resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/"+sepeteGetir.getYemek_resim_adi();
        Glide.with(mContext).load(resimUrl).into(holder.binding.imageViewSepet) ;

        holder.binding.buttonSil.setOnClickListener(v -> {
            Snackbar.make(v,sepeteGetir.getYemek_adi()+" silinsin mi?",Snackbar.LENGTH_SHORT)
                    .setAction("EVET",v1 -> {
                        viewModel.sepettenSil(Integer.parseInt(sepeteGetir.getSepet_yemek_id()));
                        sepetYemeklerList.remove(sepeteGetir);
                        notifyDataSetChanged();
                    })
                    .setActionTextColor(Color.WHITE)
                    .show();
        });


    }

    @Override
    public int getItemCount() {
        return sepetYemeklerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SepetItemBinding binding;

        public ViewHolder(SepetItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;



        }
    }
}
