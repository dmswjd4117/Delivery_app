package com.android.delivery.view.store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.android.delivery.R;
import com.android.delivery.adapter.menu.GroupMenuAdapter;
import com.android.delivery.adapter.menu.GroupMenuItem;
import com.android.delivery.adapter.menu.MenuItem;
import com.android.delivery.api.MenuApi;
import com.android.delivery.databinding.ActivityStoreBinding;
import com.android.delivery.model.menu.GroupMenuDto;
import com.android.delivery.model.menu.MenuDto;
import com.android.delivery.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// tab host : https://recipes4dev.tistory.com/115
// view, view group : https://class-programming.tistory.com/21
public class StoreActivity extends AppCompatActivity {

    private String TAG = "STORE_ACTIVITY_TAG";
    private ActivityStoreBinding binding;
    private MenuApi menuApi;
    private  ExpandableListView groupMenuListView;
    private  GroupMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Long storeId = intent.getLongExtra("storeId",  -1L);

        menuApi = RetrofitClient.createService(MenuApi.class);

        groupMenuListView = binding.storeInfoGroupMenuList;
        adapter = new GroupMenuAdapter(this);

        initTab();
        setMenuListView();
        getMenu(storeId);

    }

    private void setMenuListView(){
        groupMenuListView.setAdapter(adapter);
        groupMenuListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                setListView(parent, groupPosition);
                return false;
            }
        });
        groupMenuListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Long menuId = adapter.getMenuId(groupPosition, childPosition);
                Log.i(TAG, menuId+" ");
                return false;
            }
        });
    }


    private void getMenu(Long storeId){
        menuApi.getGroupMenuList(storeId).enqueue(new Callback<List<GroupMenuDto>>() {
            @Override
            public void onResponse(Call<List<GroupMenuDto>> call, Response<List<GroupMenuDto>> response) {
                List<GroupMenuDto> groupList = response.body();

                for(int i=0; i<groupList.size(); i++){
                    GroupMenuDto groupMenuDto = groupList.get(i);
                    Long groupMenuId = groupMenuDto.getId();

                    adapter.addGroupMenu(i, groupMenuDto);

                    Log.i(TAG, groupMenuId+" "+groupList.get(i).getTitle());
                    menuApi.getMenuList(groupMenuId).enqueue(new Callback<List<MenuDto>>() {

                        @Override
                        public void onResponse(Call<List<MenuDto>> call, Response<List<MenuDto>> response) {
                            List<MenuDto> menuList = response.body();
                            for (int i = 0; i < menuList.size(); i++) {
                                MenuDto menu = menuList.get(i);
                                adapter.addMenu(i, menu);
                            }

                            setListViewHeight();

                        }

                        @Override
                        public void onFailure(Call<List<MenuDto>> call, Throwable t) {

                        }
                    });

                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GroupMenuDto>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }

    private void setListView(ExpandableListView listView, int groupPosition){
        ExpandableListAdapter adapter = listView.getExpandableListAdapter();
    }

    private void initTab(){
        TabHost tabHost = (TabHost) binding.storeInfoTabHost;
        tabHost.setup();

        TabHost.TabSpec menuTab = tabHost.newTabSpec("menuTab");
        menuTab.setContent(R.id.storeInfo_menuContent);
        menuTab.setIndicator("메뉴");

        TabHost.TabSpec infoTab = tabHost.newTabSpec("infoTab");
        infoTab.setContent(R.id.storeInfo_infoContent);
        infoTab.setIndicator("정보");

        TabHost.TabSpec reviewTab = tabHost.newTabSpec("reviewTab");
        reviewTab.setContent(R.id.storeInfo_reviewContent);
        reviewTab.setIndicator("리뷰");

        tabHost.addTab(menuTab);
        tabHost.addTab(infoTab);
        tabHost.addTab(reviewTab);
    }

    private void setListViewHeight(){

        int result = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(groupMenuListView.getWidth(), View.MeasureSpec.AT_MOST);

        for(int i=0; i<adapter.getGroupCount(); i++){
            View groupMenuView = adapter.getGroupView(i, false, null, groupMenuListView);
            groupMenuView.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            result += groupMenuView.getMeasuredHeight();

            for (int j = 0; j < adapter.getChildrenCount(i); j++) {
                View menuListView = adapter.getChildView(i, j, false, null, groupMenuListView);

                menuListView.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                result += menuListView.getMeasuredHeight();
                Log.i("ASDASD", menuListView.getMeasuredHeight()+" !!");
            }
        }

        ViewGroup.LayoutParams params = groupMenuListView.getLayoutParams();
        params.height = result + (groupMenuListView.getDividerHeight() * (adapter.getGroupCount() - 1));

        Log.i(TAG, result+" "+params.height+" ");
        groupMenuListView.setLayoutParams(params);
        groupMenuListView.requestLayout();
    }

}