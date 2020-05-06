package com.example.fragmentpractice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fragmentpractice.databinding.FragmentButtonTitlesBinding;
import com.example.fragmentpractice.databinding.FragmentListTitlesBinding;

public class ButtonTitlesFragment extends Fragment {

    private FragmentButtonTitlesBinding binding;
    private MyViewModel model;

    public ButtonTitlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentButtonTitlesBinding.inflate(inflater, container, false);
        model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);

        binding.ramen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(0);
            }
        });

        binding.salmon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(1);
            }
        });

        binding.eggrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(2);
            }
        });

        binding.bibimyeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(3);
            }
        });

        binding.eggtoast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(4);
            }
        });

        binding.riceskewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(5);
            }
        });

        binding.asparagus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(6);
            }
        });

        binding.omurice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(7);
            }
        });

        binding.eggbread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(8);
            }
        });

        binding.eggcurry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.select(9);
            }
        });
        return binding.getRoot();
    }
}
