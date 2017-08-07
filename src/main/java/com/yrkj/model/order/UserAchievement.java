package com.yrkj.model.order;

import java.util.Date;

/**
 * Created by xuenianxiang on 2017/8/7.
 */
public class UserAchievement {

    private Long id;

    private String open_id;

    private Long achievement_id;

    private String achievement_name;

    private int achievement_type;

    private int achievement_val;

    private int integration;

    private Date create_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Long getAchievement_id() {
        return achievement_id;
    }

    public void setAchievement_id(Long achievement_id) {
        this.achievement_id = achievement_id;
    }

    public String getAchievement_name() {
        return achievement_name;
    }

    public void setAchievement_name(String achievement_name) {
        this.achievement_name = achievement_name;
    }

    public int getAchievement_type() {
        return achievement_type;
    }

    public void setAchievement_type(int achievement_type) {
        this.achievement_type = achievement_type;
    }

    public int getAchievement_val() {
        return achievement_val;
    }

    public void setAchievement_val(int achievement_val) {
        this.achievement_val = achievement_val;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
