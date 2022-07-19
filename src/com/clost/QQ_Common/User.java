package com.clost.QQ_Common;

import java.io.Serializable;

/**
 * @author clost
 * @date 2022/7/17 22:35
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId; //用户名
    private String passwd;//密码
    private String userType; // 用户类型

    public User(){

    }

    public User(String userId, String passwd,String userType) {
        this.userId = userId;
        this.passwd = passwd;
        this.userType = userType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
