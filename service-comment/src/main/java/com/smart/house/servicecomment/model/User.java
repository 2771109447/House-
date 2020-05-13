package com.smart.house.servicecomment.model;


import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class User {
    private Integer id;
    private String userName;         //用户名称
    private String phone;            //电话
    private String email;            //邮箱

    private String passwd;           //密码
    private String newPassword;      //新密码
    private String confirmPasswd;    //确认密码

    private Integer type;            //用户类型
    private Date  createTime;        //创建时间
    private String aboutme;          //自我介绍
    private String  avatar;          //用户头像
    private Integer enable;             //是否激活
    private Integer agencyId;          //经济机构Id

    private MultipartFile avatarFile;  //文件上传对象
    private String key;                //

    private String token;               //用户的jwt
    private String enableUrl;          //激活key前缀
    private String agencyName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getConfirmPasswd() {
        return confirmPasswd;
    }

    public void setConfirmPasswd(String confirmPasswd) {
        this.confirmPasswd = confirmPasswd;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getAboutme() {
        return aboutme;
    }

    public void setAboutme(String aboutme) {
        this.aboutme = aboutme;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEnableUrl() {
        return enableUrl;
    }

    public void setEnableUrl(String enableUrl) {
        this.enableUrl = enableUrl;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", passwd='" + passwd + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPasswd='" + confirmPasswd + '\'' +
                ", type=" + type +
                ", createTime=" + createTime +
                ", aboutme='" + aboutme + '\'' +
                ", avatar='" + avatar + '\'' +
                ", enable=" + enable +
                ", agencyId=" + agencyId +
                ", avatarFile=" + avatarFile +
                ", key='" + key + '\'' +
                ", token='" + token + '\'' +
                ", enableUrl='" + enableUrl + '\'' +
                ", agencyName='" + agencyName + '\'' +
                '}';
    }
}
