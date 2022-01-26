package com.android.delivery.adapter.store;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.delivery.R;
import com.android.delivery.model.store.StoreInfoDto;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StoreAdapter extends BaseAdapter {

    private class ViewHolder{
        private ImageView imageView;
        private TextView titleView;
        private TextView minimumOrderView;
        private TextView deliveryTipView;
    }

    private ArrayList<StoreItem> storeItems = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;

    public StoreAdapter(Context context) {
        this.context = context;
    }

    public void addItem(StoreInfoDto storeInfoDto){
        StoreItem storeItem = new StoreItem();
        storeItem.setImageUrl(storeInfoDto.getImageUrl());
        storeItem.setId(storeInfoDto.getId());
        storeItem.setDeliveryTip(storeInfoDto.getDeliveryTip());
        storeItem.setMinimumOrder(storeInfoDto.getMinimumOrder());
        storeItem.setTitle(storeInfoDto.getName());
        storeItems.add(storeItem);
    }

    public void removeAll() {
        storeItems.clear();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_store, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = view.findViewById(R.id.storeList_storeImage);
            viewHolder.deliveryTipView = view.findViewById(R.id.storeList_deliveryTip);
            viewHolder.minimumOrderView = view.findViewById(R.id.storeList_minimumOrder);
            viewHolder.titleView = view.findViewById(R.id.storeList_title);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        StoreItem storeItem = storeItems.get(i);

        Glide.with(view).load(storeItem.getImageUrl()).into(viewHolder.imageView);
        viewHolder.deliveryTipView.setText(storeItem.getDeliveryTip()+"");
        viewHolder.minimumOrderView.setText(storeItem.getMinimumOrder()+"");
        viewHolder.titleView.setText(storeItem.getTitle());
        return view;
    }

    @Override
    public int getCount() {
        return storeItems.size();
    }

    @Override
    public Object getItem(int i) {
        return storeItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}
