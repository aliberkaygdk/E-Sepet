package com.aliberkaygedikoglu.bitirmeproje.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aliberkaygedikoglu.bitirmeproje.R;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.Yemekler;
import com.aliberkaygedikoglu.bitirmeproje.databinding.AnasayfaItemBinding;
import com.aliberkaygedikoglu.bitirmeproje.databinding.FragmentSepetBinding;
import com.aliberkaygedikoglu.bitirmeproje.ui.fragment.AnasayfaFragmentDirections;
import com.aliberkaygedikoglu.bitirmeproje.ui.viewmodel.AnasayfaViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AnasayfaRecyclerAdapter extends RecyclerView.Adapter<AnasayfaRecyclerAdapter.ViewHolder> {

    private List<Yemekler> yemeklerList;
    private Context mContext;
    private AnasayfaViewModel viewModel;



    public AnasayfaRecyclerAdapter(List<Yemekler> yemeklerList, Context mContext, AnasayfaViewModel viewModel) {
        this.yemeklerList = yemeklerList;
        this.mContext = mContext;
        this.viewModel = viewModel;
    }
    public void filterList(ArrayList<Yemekler> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        yemeklerList = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public AnasayfaRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AnasayfaItemBinding binding =
                AnasayfaItemBinding.inflate(LayoutInflater.from(mContext),parent,false);



        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnasayfaRecyclerAdapter.ViewHolder holder, int position) {
        Yemekler yemek = yemeklerList.get(position);
        holder.binding.textViewIsim.setText(yemek.getYemek_adi());
        String resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/"+yemek.getYemek_resim_adi();
        Glide.with(mContext).load(resimUrl).into(holder.binding.imageView) ;
        holder.binding.textViewFiyat.setText(yemek.getYemek_fiyat()+" ₺");

        holder.binding.card.setOnClickListener(v -> {
            AnasayfaFragmentDirections.DetayGecis gecis = AnasayfaFragmentDirections.detayGecis(yemek);
            Navigation.findNavController(v).navigate(gecis);
        });


    }

    @Override
    public int getItemCount() {
        return yemeklerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private AnasayfaItemBinding binding;

        public ViewHolder( AnasayfaItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
