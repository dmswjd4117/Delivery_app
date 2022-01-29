package com.android.delivery.adapter.menu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.delivery.R;
import com.android.delivery.model.menu.GroupMenuDto;
import com.android.delivery.model.menu.MenuDto;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupMenuAdapter extends BaseExpandableListAdapter {
    private ArrayList<GroupMenuItem> groupMenuItems = new ArrayList<>();
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private HashMap<Integer, ArrayList<MenuItem>> childHashMap = new HashMap<>();
    private Context context;
    private GroupViewHolder groupViewHolder;
    private MenuViewHolder menuHolder;

    private class GroupViewHolder{
        private TextView title;
        private TextView description;
    }

    private class MenuViewHolder{
        private TextView nameView;
        private TextView descriptionView;
        private TextView priceView;
        private ImageView imageView;
    }

    public GroupMenuAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return groupMenuItems.size();
    }


    @Override
    public Object getGroup(int i) {
        return groupMenuItems.get(i);
    }


    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.group_menu, viewGroup, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.description = view.findViewById(R.id.itemGroupMenu_description);
            groupViewHolder.title = view.findViewById(R.id.itemGroupMenu_title);
            view.setTag(groupViewHolder);
        }else{
            groupViewHolder = (GroupViewHolder) view.getTag();
        }

        GroupMenuItem groupMenuItem = groupMenuItems.get(i);

        groupViewHolder.title.setText(groupMenuItem.getTitle());
        groupViewHolder.description.setText(groupMenuItem.getDescription());

        return view;
    }



    @Override
    public int getChildrenCount(int i) {
        return childHashMap.get(i).size();
    }

    @Override
    public MenuItem getChild(int i, int i1) {
        return childHashMap.get(i).get(i1);
    }
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
    @Override
    public View getChildView(int parent_index, int child_index, boolean b, View view, ViewGroup viewGroup) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.menu, viewGroup, false);
            menuHolder = new MenuViewHolder();
            menuHolder.descriptionView = view.findViewById(R.id.itemMenu_description);
            menuHolder.imageView = view.findViewById(R.id.itemMenu_image);
            menuHolder.nameView = view.findViewById(R.id.itemMenu_name);
            menuHolder.priceView = view.findViewById(R.id.itemMenu_price);
            view.setTag(menuHolder);
        }else{
            menuHolder = (MenuViewHolder) view.getTag();
        }

        MenuItem item = childHashMap.get(parent_index).get(child_index);
        menuHolder.nameView.setText(item.getName());
        menuHolder.descriptionView.setText(item.getDescription());
        menuHolder.priceView.setText(item.getPrice()+"");
        if(!item.getPhoto().equals("")){
            Glide.with(view).load(item.getPhoto()).into(menuHolder.imageView);
        }

        return view;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    public void addGroupMenu(int groupPosition, GroupMenuDto groupMenuDto){
        GroupMenuItem item = new GroupMenuItem();
        item.setTitle(groupMenuDto.getTitle());
        item.setDescription(groupMenuDto.getDescription());
        item.setId(groupMenuDto.getId());
        groupMenuItems.add(item);

        if(!childHashMap.containsKey(groupPosition)){
            childHashMap.put(groupPosition, new ArrayList<>());
        }
    }


    public void addMenu(int parent_index, MenuDto menuDto){
        MenuItem menuItem = new MenuItem();
        menuItem.setDescription(menuDto.getDescription());
        menuItem.setId(menuDto.getId());
        menuItem.setName(menuDto.getName());
        menuItem.setPhoto(menuDto.getPhoto());
        menuItem.setPrice(menuDto.getPrice());

        childHashMap.get(parent_index).add(menuItem);
    }

    public Long getMenuId(int p, int c){
        return childHashMap.get(p).get(c).getId();
    }




}
