package com.android.delivery.adapter.store;

public class StoreItem {


    private Long id;
    private String imageUrl;
    private Long minimumOrder;
    private Long deliveryTip;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getMinimumOrder() {
        return minimumOrder;
    }

    public void setMinimumOrder(Long minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    public Long getDeliveryTip() {
        return deliveryTip;
    }

    public void setDeliveryTip(Long deliveryTip) {
        this.deliveryTip = deliveryTip;
    }



}
