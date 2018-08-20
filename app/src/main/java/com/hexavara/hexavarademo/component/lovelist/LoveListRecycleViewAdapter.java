package com.hexavara.hexavarademo.component.lovelist;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hexavara.hexavarademo.BR;
import com.hexavara.hexavarademo.R;
import com.hexavara.hexavarademo.databinding.LayoutLoveItemBinding;
import com.hexavara.hexavarademo.model.LoveItemModel;

import java.util.ArrayList;

public class LoveListRecycleViewAdapter extends RecyclerView.Adapter<LoveListRecycleViewAdapter.ViewHolder> {

    ArrayList<LoveItemModel> items;

    LoveListRecycleViewAdapter(ArrayList<LoveItemModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LayoutLoveItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_love_item, parent, false);
        return new ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LoveItemModel item = items.get(position);
        LoveItemViewModel itemViewModel = new LoveItemViewModel(item);
        holder.bind(itemViewModel);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Object obj) {
            //Make sure variable on layout is `loveItemViewModel`
            binding.setVariable(BR.loveItemViewModel, obj);
            binding.executePendingBindings();
        }
    }
}
