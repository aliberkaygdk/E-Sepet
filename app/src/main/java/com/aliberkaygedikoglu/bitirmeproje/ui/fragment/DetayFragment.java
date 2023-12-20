package com.aliberkaygedikoglu.bitirmeproje.ui.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.aliberkaygedikoglu.bitirmeproje.MainActivity;
import com.aliberkaygedikoglu.bitirmeproje.R;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.Yemekler;
import com.aliberkaygedikoglu.bitirmeproje.databinding.FragmentDetayBinding;
import com.aliberkaygedikoglu.bitirmeproje.ui.viewmodel.DetayViewModel;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class DetayFragment extends Fragment {


    private FragmentDetayBinding binding;
    private DetayViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetayBinding.inflate(inflater, container, false);


        Toolbar toolbar = binding.toolbar2;
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getOnBackPressedDispatcher().onBackPressed();

            }
        });


        DetayFragmentArgs bundle = DetayFragmentArgs.fromBundle(getArguments());
        Yemekler gelenYemek = bundle.getYemek();

        binding.textViewDetayAd.setText(gelenYemek.getYemek_adi());
        binding.textViewDetayFiyat.setText(gelenYemek.getYemek_fiyat());
        binding.textViewTutar.setText(gelenYemek.getYemek_fiyat() + " ₺");

        String resimUrl = "http://kasimadalan.pe.hu/yemekler/resimler/" + gelenYemek.getYemek_resim_adi();
        Glide.with(requireContext()).load(resimUrl).into(binding.imageViewDetay);


        binding.buttonAzalt.setOnClickListener(v -> {
            String[] words = binding.textViewAdet.getText().toString().split(" ");
            if (Integer.parseInt(words[0]) > 1) {
                int azalt = Integer.parseInt(words[0]) - 1;
                binding.textViewAdet.setText(String.valueOf(azalt) + " Adet");

                int fiyat = Integer.parseInt(binding.textViewDetayFiyat.getText().toString()) * azalt;
                binding.textViewTutar.setText(String.valueOf(fiyat) + " ₺");
            } else {
                Snackbar.make(v, "Minimum 1 adet olmalıdır", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.buttonArttir.setOnClickListener(v -> {
            String[] words = binding.textViewAdet.getText().toString().split(" ");
            int arttir = Integer.parseInt(words[0]) + 1;

            binding.textViewAdet.setText(String.valueOf(arttir) + " Adet");

            int fiyat = Integer.parseInt(binding.textViewDetayFiyat.getText().toString()) * arttir;
            binding.textViewTutar.setText(String.valueOf(fiyat) + " ₺");
        });

        binding.buttonSepeteEkle.setOnClickListener(v -> {
            String[] words = binding.textViewAdet.getText().toString().split(" ");
            String yemek_adi = binding.textViewDetayAd.getText().toString();
            String yeme_resim_adi = gelenYemek.getYemek_resim_adi();
            int yemek_fiyat = Integer.parseInt(gelenYemek.getYemek_fiyat());
            int yemek_siparis_adet = Integer.parseInt(words[0]);
            String kullanici_adi = "aliberkaygdk";


            viewModel.sepeteEkle(yemek_adi, yeme_resim_adi, yemek_fiyat, yemek_siparis_adet);

            // Snackbar.make(v,"Ürün Sepete Eklendi", Snackbar.LENGTH_SHORT).show();


            EkleDialogFragment ekleDialogFragment = new EkleDialogFragment();
            ekleDialogFragment.show(requireActivity().getSupportFragmentManager(), "ekleDialog");


        });


        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DetayViewModel.class);
    }


}
