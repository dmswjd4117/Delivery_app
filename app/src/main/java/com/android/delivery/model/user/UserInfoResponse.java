package com.android.delivery.model.user;

public class UserInfoResponse {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    private Long id;

    private String email;

    private String phone;

    private String name;

    private String image;


}
