package com.android.delivery.model.address;

public class AddressRequest {
    public AddressRequest(String cityName, String cityCountyName, String roadName, String buildingNameForCity) {
        this.cityName = cityName;
        this.cityCountyName = cityCountyName;
        this.roadName = roadName;
        this.buildingNameForCity = buildingNameForCity;
    }

    public String cityName;
    public String cityCountyName;
    public String roadName;
    public String buildingNameForCity;


    public String getCityName() {
        return cityName;
    }

    public String getCityCountyName() {
        return cityCountyName;
    }

    public String getRoadName() {
        return roadName;
    }

    public String getBuildingNameForCity() {
        return buildingNameForCity;
    }


}
