package com.example.receiver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.receiver.databinding.ItemBinding;

import java.util.List;

class ViewHolder extends RecyclerView.ViewHolder {
    ItemBinding mBinding;
    ViewHolder(ItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;

    }
}

public class SMSAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<SMS> mMessages;

    SMSAdapter(List<SMS> messages) {
        mMessages = messages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemBinding binding = ItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SMS message = mMessages.get(position);
        holder.mBinding.textView.setText(message.mInfo);
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }
}
