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

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public static String getRoadNameAddress(AddressResponse response){
        String res = response.getCityName()+" "
                +response.getCityCountyName()+" "
                +response.getRoadName()+" "
                +response.getBuildingNum()+" "
                +response.getBuildingNameForCity()+" "
                +response.getDetailBuildingName();
        return res;
    }

    String cityName;
    String cityCountyName;
    String townName;
    String roadName;
    String buildingManagementNum;
    String buildingNameForCity;
    String detailBuildingName;
    String buildingNum;

}
