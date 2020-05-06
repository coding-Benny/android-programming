package com.example.fragmentpractice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmentpractice.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        MyViewModel model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        model.getSelected().observe(this, idx-> {
            if(idx >= 0) {
                binding.textView.setText(Recipes.EXPLANATION[idx]);
                binding.imageView.setImageResource(Recipes.PREVIEWS[idx]);
            }
        });
    }
}
