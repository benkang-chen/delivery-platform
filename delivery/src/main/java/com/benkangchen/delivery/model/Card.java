package com.benkangchen.delivery.model;

import java.util.Date;

public class Card {
    private Integer id;

    private Integer cardNum;

    private String cradPasswd;

    private Integer type;

    private Byte state;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public String getCradPasswd() {
        return cradPasswd;
    }

    public void setCradPasswd(String cradPasswd) {
        this.cradPasswd = cradPasswd == null ? null : cradPasswd.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}