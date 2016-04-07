package com.blue.xiangzishen.com.blue.xiangzishen.bean;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by blue on 16-4-2.
 */
public class User extends BmobUser {
    private int age;
    private boolean sex;
    private BmobFile pic;

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public BmobFile getPic() {
        return pic;
    }

    public void setPic(BmobFile pic) {
        this.pic = pic;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
