package com.android.delivery.model.user;

public class AddressUpdateRequest {

    public AddressUpdateRequest(String buildingManagementNum, String address) {
        this.buildingManagementNum = buildingManagementNum;
        this.address = address;
    }

    String buildingManagementNum;
    String address;


}
