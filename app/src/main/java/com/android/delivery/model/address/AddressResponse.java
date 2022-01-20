package com.android.delivery.model.address;



public class AddressResponse {

    public AddressResponse(String cityName, String cityCountyName, String townName, String roadName, String buildingManagementNum, String buildingNameForCity, String detailBuildingName) {
        this.cityName = cityName;
        this.cityCountyName = cityCountyName;
        this.townName = townName;
        this.roadName = roadName;
        this.buildingManagementNum = buildingManagementNum;
        this.buildingNameForCity = buildingNameForCity;
        this.detailBuildingName = detailBuildingName;
    }



    public String getCityName() {
        return cityName;
    }

    public String getCityCountyName() {
        return cityCountyName;
    }

    public String getTownName() {
        return townName;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getBuildingManagementNum() {
        return buildingManagementNum;
    }

    public String getBuildingNameForCity() {
        return buildingNameForCity;
    }

    public String getDetailBuildingName() {
        return detailBuildingName;
    }

    String cityName;
    String cityCountyName;
    String townName;
    String roadName;
    String buildingManagementNum;
    String buildingNameForCity;
    String detailBuildingName;

}
