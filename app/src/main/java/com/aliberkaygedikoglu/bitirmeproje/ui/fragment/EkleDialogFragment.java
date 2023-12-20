package com.aliberkaygedikoglu.bitirmeproje.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliberkaygedikoglu.bitirmeproje.R;
import com.aliberkaygedikoglu.bitirmeproje.databinding.FragmentEkleDialogBinding;


public class EkleDialogFragment extends DialogFragment {

    private FragmentEkleDialogBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEkleDialogBinding.inflate(inflater, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);


        binding.buttonEnableNFC.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().remove(EkleDialogFragment.this).commit();
        });



        return binding.getRoot();
    }
}