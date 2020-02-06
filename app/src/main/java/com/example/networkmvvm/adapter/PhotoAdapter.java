package com.example.networkmvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.networkmvvm.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoHolder> {
    private List<String> photoModels;
    private GridLayoutManager mLayoutManager;

    public PhotoAdapter(List<String> photoModels, GridLayoutManager layoutManager) {
        this.photoModels = photoModels;
        mLayoutManager = layoutManager;
    }

    @NonNull
    @Override
    public PhotoAdapter.PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view;
        if(viewType == 2){
            view = LayoutInflater.from(context).inflate(R.layout.row_item,parent,false);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.square_row_item,parent,false);
        }
        return new PhotoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.PhotoHolder holder, int position) {
        Glide.with(holder.imageView.getContext())
                .load(photoModels.get(position))
                .into(holder.imageView);
    }
    @Override
    public int getItemViewType(int position) {
        int spanCount = mLayoutManager.getSpanCount();
        if(spanCount == 1){
            return 2;
        }else{
            return 1;
        }
    }
    @Override
    public int getItemCount() {
        return photoModels.size();
    }

    public class PhotoHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public PhotoHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tvDisplay);
        }
    }
}
