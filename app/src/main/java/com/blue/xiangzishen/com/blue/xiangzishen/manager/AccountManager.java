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
    public static StateListener mState;
    public static final String MODE_LOGIN = "login";
    public static final String MODE_SIGN = "sign";

    public void setListener(StateListener state) {
        this.mState = state;
    }

    public static void login(final Context context, String user, String pwd) {
        BmobUser.loginByAccount(context, user, pwd, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (user != null) {
                    if (mState != null) {
                        mState.getState(MODE_LOGIN, true);
                    }
                } else {
                    if (mState != null) {
                        mState.getState(MODE_LOGIN, false);
                    }
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
                if (mState != null) {
                    mState.getState(MODE_SIGN, true);
                }
            }

            @Override
            public void onFailure(int i, String s) {
                if (mState != null) {
                    mState.getState(MODE_SIGN, false);
                }
            }
        });
    }
}
