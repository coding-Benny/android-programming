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

import com.example.fragmentpractice.databinding.FragmentListTitlesBinding;

public class ListTitlesFragment extends Fragment {

    private FragmentListTitlesBinding binding;
    private MyViewModel model;

    public ListTitlesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListTitlesBinding.inflate(inflater, container, false);
        model = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        binding.listView.setAdapter(new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_activated_1, Recipes.TITLES));
        binding.listView.setOnItemClickListener((adapterView, view, i, l) -> model.select(i));
        binding.listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        return binding.getRoot();
    }
}
