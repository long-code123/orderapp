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
import com.app.orderfood.models.Category;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    private ArrayList<Category> items;
    private Context context;
    private OnClickCategory onClickCategory;

    public CategoryAdapter(ArrayList<Category> items, OnClickCategory onClickCategory) {
        this.items = items;
        this.onClickCategory = onClickCategory;
    }

    @NonNull
    @Override
    public CategoryAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_categories, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Category item = items.get(position);

        holder.titleTxt_category.setText(item.getName());

        Glide.with(context)
                .load(item.getImagePath())
                //.transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.pic_category);


        holder.viewAll_category.setOnClickListener(v -> {
            onClickCategory.clickItem(item, position);
        });
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<Category> list) {
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
        TextView titleTxt_category;
        ImageView pic_category;

        CardView viewAll_category;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            viewAll_category = itemView.findViewById(R.id.viewAll_category);
            titleTxt_category = itemView.findViewById(R.id.titleTxt_category);
            pic_category = itemView.findViewById(R.id.pic_category);

        }
    }
}
