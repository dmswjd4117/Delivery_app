package com.android.delivery.adapter.menu;

import android.widget.ListView;

public class GroupMenuItem {


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    private Long id;
    private String title;
    private String description;


}
