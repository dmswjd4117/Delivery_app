package com.android.delivery.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;

import com.android.delivery.databinding.ActivityMainBinding;
import com.android.delivery.view.address.AddressSettingActivity;
import com.android.delivery.view.main.MainActivity;

// https://emptybox031.tistory.com/entry/%ED%95%98%EB%82%98%EC%9D%98-%ED%88%B4%EB%B0%94%EB%A5%BC-%EC%97%AC%EB%9F%AC%EA%B0%9C%EC%9D%98-%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0%EC%97%90-%EB%8F%99%EC%9D%BC%ED%95%98%EA%B2%8C-%EC%A0%81%EC%9A%A9%ED%95%98

public class ToolBarSetting {
    private Toolbar toolbar;

    public ToolBarSetting(Toolbar toolbar){
        this.toolbar = toolbar;
    }


    public void setTitle(androidx.appcompat.app.ActionBar actionBar, Context context){
        actionBar.setDisplayShowCustomEnabled(true);

        String title = PreferenceManger.getString(context, "addressTitle")+" ▼";
        if(title.equals("")){
            PreferenceManger.setString(context, "addressTitle", "부경대학교");
            PreferenceManger.setString(context, "addressCode", "2629010600");
            PreferenceManger.setString(context,"detailAddress", "청운관");
            PreferenceManger.setString(context, "roadNameAddress", "수영로358번길");
            title = "부경대학교 ▼";
        }

        actionBar.setTitle(title);

        toolbar.setOnClickListener(view -> {
            Intent intent = new Intent(context, AddressSettingActivity.class);
            context.startActivity(intent);
        });
    }


}
