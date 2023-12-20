package com.aliberkaygedikoglu.bitirmeproje.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliberkaygedikoglu.bitirmeproje.Bildirim;
import com.aliberkaygedikoglu.bitirmeproje.R;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.SepeteGetir;
import com.aliberkaygedikoglu.bitirmeproje.databinding.FragmentSepetBinding;
import com.aliberkaygedikoglu.bitirmeproje.ui.adapter.SepetRecyclerAdapter;
import com.aliberkaygedikoglu.bitirmeproje.ui.viewmodel.SepetViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.TimeUnit;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class SepetFragment extends Fragment {

    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSepetBinding.inflate(inflater, container, false);




        binding.rcSepet.setLayoutManager(new LinearLayoutManager(requireContext()));

        viewModel.sepetListesi.observe(getViewLifecycleOwner(),liste -> {

            SepetRecyclerAdapter adapter = new SepetRecyclerAdapter(liste,requireContext(),viewModel);
            binding.rcSepet.setAdapter(adapter);

            int toplam = 0;
            for (SepeteGetir sepet:liste) {

               int tutar = Integer.parseInt(sepet.getYemek_fiyat()) * Integer.parseInt(sepet.getYemek_siparis_adet());
               toplam = toplam+tutar;

            }
            binding.textViewToplam.setText(toplam+" ₺");



            binding.buttonSepet.setOnClickListener(v -> {

                if (liste.isEmpty()){
                    Snackbar.make(v,"Sepet boş!",Snackbar.LENGTH_SHORT).setBackgroundTint(getResources().getColor(R.color.buttoncolor)).setTextColor(Color.WHITE).show();

                }else {

                    SepetDialogFragment sepetDialogFragment = new SepetDialogFragment();
                    sepetDialogFragment.show(requireActivity().getSupportFragmentManager(), "dialog");


                    WorkRequest istek = new OneTimeWorkRequest.Builder(Bildirim.class)
                            .setInitialDelay(3, TimeUnit.SECONDS)
                            .build();

                    WorkManager.getInstance(requireContext()).enqueue(istek);
                }
            });
        });







        return binding.getRoot();

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SepetViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.sepetGetir();

    }
}