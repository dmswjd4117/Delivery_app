package com.android.delivery.adapter.option;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.delivery.R;
import com.android.delivery.model.menu.OptionDto;

import java.util.ArrayList;

public class OptionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<OptionItem> optionList = new ArrayList<>();
    private ViewHolder viewHolder;

    public OptionAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return optionList.size();
    }

    @Override
    public Object getItem(int i) {
        return optionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(OptionDto option) {
        OptionItem optionItem = new OptionItem();
        optionItem.setMenuId(option.getMenuId());
        optionItem.setPrice(option.getPrice());
        optionItem.setName(option.getName());
        optionList.add(optionItem);
    }


    private class ViewHolder{
        private TextView nameView;
        private TextView priceView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_option, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.nameView = view.findViewById(R.id.itemOption_name);
            viewHolder.priceView = view.findViewById(R.id.itemOption_price);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        OptionItem optionItem = optionList.get(i);

        Log.i("VIEW_HOLDER", optionItem.getName());
        viewHolder.nameView.setText(optionItem.getName());
        viewHolder.priceView.setText(optionItem.getPrice()+"");

        return view;
    }
}
