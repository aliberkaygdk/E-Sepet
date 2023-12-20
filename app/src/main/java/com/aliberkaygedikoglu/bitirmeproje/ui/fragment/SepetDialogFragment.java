package com.aliberkaygedikoglu.bitirmeproje.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliberkaygedikoglu.bitirmeproje.R;
import com.aliberkaygedikoglu.bitirmeproje.databinding.FragmentSepetDialogBinding;


public class SepetDialogFragment extends DialogFragment {



    private FragmentSepetDialogBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSepetDialogBinding.inflate(inflater, container, false);


        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);


        binding.buttonSepetDialog.setOnClickListener(v -> {
            getParentFragmentManager().beginTransaction().remove(SepetDialogFragment.this).commit();
        });



        return binding.getRoot();
    }
}