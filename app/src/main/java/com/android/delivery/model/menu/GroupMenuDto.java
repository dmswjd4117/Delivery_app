package com.android.delivery.model.menu;

public class GroupMenuDto {


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getStoreId() {
        return storeId;
    }

    private Long id;
    private String title;
    private String description;
    private Long storeId;
}
