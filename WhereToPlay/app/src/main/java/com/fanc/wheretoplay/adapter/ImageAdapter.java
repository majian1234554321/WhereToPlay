package com.fanc.wheretoplay.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fanc.wheretoplay.R;
import com.fanc.wheretoplay.imagepink.utils.ImageSelectorUtils;


import java.io.File;
import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context mContext;

    private LayoutInflater mInflater;
    ArrayList<String> imagesfinal;

    public ImageAdapter(Context context, ArrayList<String> imagesfinal) {
        mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.imagesfinal = imagesfinal;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.adapter_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (imagesfinal.get(position).endsWith("这不是图片")) {
            Glide.with(mContext).load(R.drawable.addjpg).into(holder.ivImage);
        } else {
            final String image = imagesfinal.get(position);
            Glide.with(mContext).load(new File(image)).into(holder.ivImage);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagesfinal.get(position).endsWith("这不是图片")) {
                    ImageSelectorUtils.openPhoto((Activity) mContext, 0x00000011, false, 9);
                } else {
                    Toast.makeText(mContext, position + "AAAAA", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return imagesfinal.size() >= 9 ? 9 : imagesfinal.size();
    }

    public void refresh(ArrayList<String> images) {
        imagesfinal = images;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        }
    }
}
