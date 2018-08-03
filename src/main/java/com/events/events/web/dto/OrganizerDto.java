package com.events.events.web.dto;

public class OrganizerDto extends  UserDto {

    private long id;

    private String mobile ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
