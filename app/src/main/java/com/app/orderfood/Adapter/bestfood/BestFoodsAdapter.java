package com.app.orderfood.Adapter.bestfood;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.orderfood.R;
import com.app.orderfood.models.Foods;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BestFoodsAdapter extends RecyclerView.Adapter<BestFoodsAdapter.viewholder> {
    ArrayList<Foods> items;
    Context context;
    OnClickBestFood onClickBestFood;

    public BestFoodsAdapter(ArrayList<Foods> items, OnClickBestFood onClickBestFood) {
        this.items = items;
        this.onClickBestFood = onClickBestFood;
    }

    @NonNull
    @Override
    public BestFoodsAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_best_deal, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestFoodsAdapter.viewholder holder, int position) {

        Foods item = items.get(position);

        holder.titleTxt.setText(item.getTitle());
        holder.priceTxt.setText("$"+item.getPrice());
        holder.timeTxt.setText(item.getTimeValue() + "min");
        holder.starTxt.setText(""+item.getStar());

        Glide.with(context)
                .load(item.getImagePath())
                //.transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic);


        holder.viewAll.setOnClickListener(v -> {
            onClickBestFood.clickItem(item, position);
        });
        holder.btnPlus.setOnClickListener(view -> {
            
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Foods> list) {
        items.clear();
        items.addAll(list);

        //update Ui vs dữ liệu mới
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView titleTxt, priceTxt, starTxt, timeTxt, btnPlus;
        ImageView pic;


        CardView viewAll;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            viewAll = itemView.findViewById(R.id.viewAll);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            starTxt = itemView.findViewById(R.id.starTxt);
            timeTxt = itemView.findViewById(R.id.timeTxt);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            pic = itemView.findViewById(R.id.pic);


        }
    }
}
