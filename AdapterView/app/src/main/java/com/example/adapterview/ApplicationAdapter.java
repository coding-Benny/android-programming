package com.example.adapterview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adapterview.databinding.ItemBinding;
import java.util.List;

class ViewHolder extends RecyclerView.ViewHolder {
    ItemBinding mBinding;
    ViewHolder(ItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
        mBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = getAdapterPosition();
                if(i != RecyclerView.NO_POSITION)
                    Log.i("RV", String.format("%d %s", i, mBinding.label.getText()));
            }
        });
    }
}

public class ApplicationAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Application> mApps;

    ApplicationAdapter(List<Application> apps) {
        mApps = apps;
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
        Application app = mApps.get(position);
        holder.mBinding.icon.setImageDrawable(app.mIcon);
        holder.mBinding.label.setText(app.mLabel);
        holder.mBinding.packageName.setText(app.mPackageName);
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }
}
