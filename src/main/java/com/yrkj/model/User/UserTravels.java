package com.yrkj.model.User;

import java.util.Date;

/**
 * Created by xuenianxiang on 2017/8/19.
 */
public class UserTravels {

    private Long id;

    private String open_id;

    private Long travels_id;

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

    public Long getTravels_id() {
        return travels_id;
    }

    public void setTravels_id(Long travels_id) {
        this.travels_id = travels_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
