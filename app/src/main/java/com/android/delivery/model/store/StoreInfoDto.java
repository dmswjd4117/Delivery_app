package com.android.delivery.model.store;

public class StoreInfoDto {
    private Long id;
    private String name;
    private String phone;
    private Long categoryId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuildingManagementNum() {
        return buildingManagementNum;
    }

    public void setBuildingManagementNum(String buildingManagementNum) {
        this.buildingManagementNum = buildingManagementNum;
    }

    private String address;
    private String buildingManagementNum;
}
