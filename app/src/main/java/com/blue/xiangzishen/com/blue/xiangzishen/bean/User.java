package com.blue.xiangzishen.com.blue.xiangzishen.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by blue on 16-4-2.
 */
public class User extends BmobObject {
    private String name;
    private String pwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
