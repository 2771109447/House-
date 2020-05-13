package com.smart.house.apigateway.model;

import java.util.Date;

public class UserMsg {
    private Integer id;
    private String msg;      //用户留言
    private Integer  userId; //用户id
    private String userName;  //用户姓名
    private Date createTime; //创建时间
    private Integer  agentId;//经纪人id
    private Integer  houseId;//房产id
    private String email;    //经纪人邮箱
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserMsg{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                ", agentId=" + agentId +
                ", houseId=" + houseId +
                ", email='" + email + '\'' +
                '}';
    }

}
