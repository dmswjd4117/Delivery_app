package com.android.delivery.model.user;


public class UpdateUserInfoRequest {
    private String name;
    private String curPassword;
    private String newPassword;
    private boolean changePassword;

    public void setName(String name) {
        this.name = name;
    }

    public void setCurPassword(String curPassword) {
        this.curPassword = curPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }
}
