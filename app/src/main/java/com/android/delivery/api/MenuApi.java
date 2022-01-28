package com.android.delivery.api;

import com.android.delivery.model.menu.GroupMenuDto;
import com.android.delivery.model.menu.MenuDto;
import com.android.delivery.model.menu.OptionDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MenuApi {
    @GET("/menu_info/group/get")
    Call<List<GroupMenuDto>> getGroupMenuList(@Query("storeId") Long storeId);

    @GET("/menu_info/menu/get")
    Call<List<MenuDto>> getMenuList(@Query("groupMenuId") Long groupMenuId);

    @GET("/menu_info/option/get")
    Call<List<OptionDto>> getOptionList(@Query("menuId") Long menuId);

}
