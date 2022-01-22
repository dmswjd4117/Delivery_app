package com.android.delivery.adapter.address;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.delivery.R;

import java.util.ArrayList;

public class AddressSearchAdapter extends BaseAdapter {

    private ArrayList<AddressSearchItem> addressItems = new ArrayList<>();
    private Context context;
    private ViewHolder viewHolder;

    public AddressSearchAdapter(Context context){
        this.context = context;
    }

    private class ViewHolder{
        private TextView titleView;
        private TextView roadNameView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.address_search_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.roadNameView = view.findViewById(R.id.addressSearch_item_roadNameAddress);
            viewHolder.titleView = view.findViewById(R.id.addressSearch_item_title);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        AddressSearchItem addressSearchItem = addressItems.get(i);

        viewHolder.titleView.setText(addressSearchItem.getTitle());
        viewHolder.roadNameView.setText(addressSearchItem.getRoadNameAddress());

        return view;
    }

    @Override
    public int getCount() {
        return addressItems.size();
    }

    @Override
    public Object getItem(int i) {
        return addressItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addItem(String title, String roadNameAddress, String buildingManagementNum){
        AddressSearchItem item = new AddressSearchItem();

        item.setRoadNameAddress(roadNameAddress);
        item.setTitle(title);
        item.setBuildingManagementNum(buildingManagementNum);

        addressItems.add(item);
    }

    public void removeAll(){
        addressItems.clear();
    }


}
