package com.yrkj.model.clientele;

/**
 * Created by xuenianxiang on 2017/8/13.
 */
public class ClienteleDto {

    private String nick_name;

    private String mobile;

    private String sex;

    private String birthday;

    private String city;

    private int achievement_val;

    private int integration_val;

    private int badge_count;

    private String designation;

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAchievement_val() {
        return achievement_val;
    }

    public void setAchievement_val(int achievement_val) {
        this.achievement_val = achievement_val;
    }

    public int getIntegration_val() {
        return integration_val;
    }

    public void setIntegration_val(int integration_val) {
        this.integration_val = integration_val;
    }

    public int getBadge_count() {
        return badge_count;
    }

    public void setBadge_count(int badge_count) {
        this.badge_count = badge_count;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
