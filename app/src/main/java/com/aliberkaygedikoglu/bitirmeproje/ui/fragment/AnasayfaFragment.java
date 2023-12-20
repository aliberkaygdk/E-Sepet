package com.aliberkaygedikoglu.bitirmeproje.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliberkaygedikoglu.bitirmeproje.LoginActivity;
import com.aliberkaygedikoglu.bitirmeproje.MainActivity;
import com.aliberkaygedikoglu.bitirmeproje.data.entity.Yemekler;
import com.aliberkaygedikoglu.bitirmeproje.databinding.FragmentAnasayfaBinding;
import com.aliberkaygedikoglu.bitirmeproje.ui.adapter.AnasayfaRecyclerAdapter;
import com.aliberkaygedikoglu.bitirmeproje.ui.viewmodel.AnasayfaViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class AnasayfaFragment extends Fragment{

    private AnasayfaViewModel viewModel;
    private FragmentAnasayfaBinding binding;
    private List<Yemekler> yemeklers;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);




        binding.rc.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(),yemeklerListesi -> {
            AnasayfaRecyclerAdapter adapter = new AnasayfaRecyclerAdapter(yemeklerListesi,requireContext(),viewModel);

            binding.rc.setAdapter(adapter);

            binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    ArrayList<Yemekler> yemekFiltre = new ArrayList<>() ;
                    for (Yemekler item : yemeklerListesi) {
                        // checking if the entered string matched with any item of our recycler view.
                        if (item.getYemek_adi().toLowerCase().contains(newText.toLowerCase())) {

                            yemekFiltre.add(item);
                        }
                    }
                    adapter.filterList(yemekFiltre);

                    return false;
                }
            });

        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(requireActivity(), LoginActivity.class);

                startActivity(intent);
                getActivity().finish();


            }
        });




        return binding.getRoot();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
    }



   /*@Override
    public void onResume() {
        super.onResume();
        viewModel.yemekleriYukle();


    }*/





}