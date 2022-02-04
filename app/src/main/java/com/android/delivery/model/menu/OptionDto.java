package com.android.delivery.model.menu;

public class OptionDto {
    private Long id;
    private String name;
    private Long menuId;
    private Long price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
