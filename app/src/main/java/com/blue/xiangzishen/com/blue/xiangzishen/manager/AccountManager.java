package com.blue.xiangzishen.com.blue.xiangzishen.manager;

import android.content.Context;
import android.widget.Toast;

import com.blue.xiangzishen.com.blue.xiangzishen.bean.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by blue on 16-4-7.
 */
public class AccountManager {

    public static void login(final Context context, String user, String pwd) {
        BmobUser.loginByAccount(context, user, pwd, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (user != null) {
                    Toast.makeText(context.getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context.getApplicationContext(), "The phone number or password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void sign(final Context context, String user, String pwd, String phone) {
        User myUser = new User();
        myUser.setUsername(user);
        myUser.setPassword(pwd);
        myUser.setMobilePhoneNumber(phone);
        myUser.setMobilePhoneNumberVerified(true);
        myUser.signUp(context, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context.getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
