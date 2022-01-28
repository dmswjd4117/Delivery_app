package com.android.delivery.adapter.menu;

public class MenuItem {
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public Long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private Long id;
    private String name;
    private Long price;
    private String description;
    private String photo;

}
