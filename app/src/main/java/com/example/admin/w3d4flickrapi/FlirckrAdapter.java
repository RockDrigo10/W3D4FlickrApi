package com.example.admin.w3d4flickrapi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.w3d4flickrapi.model.Item;

import java.util.ArrayList;

public class FlirckrAdapter extends RecyclerView.Adapter<FlirckrAdapter.ViewHolder> {
    private static final String TAG = "Adapter";
    ArrayList<Item> itemList = new ArrayList<>();
    Context context;

    public FlirckrAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvText, tvText2,tvText3;

        public ViewHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
            tvText = (TextView) itemView.findViewById(R.id.tvText);
            tvText2 = (TextView) itemView.findViewById(R.id.tvText2);
            tvText3 = (TextView) itemView.findViewById(R.id.tvText3);
        }
    }

    @Override
    public FlirckrAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.flckr_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FlirckrAdapter.ViewHolder holder, int position) {
        final Item item = itemList.get(position);
        Log.d(TAG, "onBindViewHolder: " + item.getMedia().getM());
        holder.tvText.setText(item.getTitle());
        holder.tvText2.setText(item.getDateTaken());
        holder.tvText3.setText(item.getLink());
        Glide.with(holder.itemView.getContext()).load(item.getMedia().getM()).into(holder.ivImage);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                new AlertDialog.Builder(v.getContext())
                        .setIcon(R.drawable.dortmund)
                        .setTitle("Options")
                        .setMessage("Display image:")
                        .setNegativeButton("Small Image", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final Dialog dialogCustom = new Dialog(v.getContext());
                                dialogCustom.setContentView(R.layout.thumb);
                                ImageView ivThumb = (ImageView) dialogCustom.findViewById(R.id.ivThumb);
                                Glide.with(v.getContext()).load(item.getMedia().getM()).into(ivThumb);
                                dialogCustom.show();
                            }
                        })
                        .setPositiveButton("Full Image", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(v.getContext(),ImageActivity.class);
                                intent.putExtra("image",item.getMedia().getM());
                                v.getContext().startActivity(intent);
                            }
                        })
                        .show();

                return true;
            }
        });


        Log.d(TAG, "onBindViewHolder: " + position);
    }

    @Override
    public int getItemCount() {
        //Log.d(TAG, "getItemCount: " + flickrList.size());
        return itemList.size();
    }

}
