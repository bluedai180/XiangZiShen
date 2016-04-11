package com.blue.xiangzishen.com.blue.xiangzishen.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.blue.xiangzishen.com.blue.xiangzishen.bean.User;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by blue on 16-4-7.
 */
public class AccountManager {
    public static StateListener mState;
    public static SearchUserListener mUser;
    public static final String MODE_LOGIN = "login";
    public static final String MODE_SIGN = "sign";
    public static final String MODE_SEARCH_USER = "search_user";
    public static final String MODEL_RESET_PWD = "reset_pwd";

    public void setListener(StateListener state) {
        this.mState = state;
    }

    public void setUserListener(SearchUserListener user) {
        this.mUser = user;
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

    public static void getCurrentUser(Context content) {
        User user = BmobUser.getCurrentUser(content, User.class);
        user.getObjectId();
        if (mUser != null) {
            mUser.getUser(user);
        }
    }

    public static void searchUser(Context context, String key, String value) {
        BmobQuery<BmobUser> query = new BmobQuery<BmobUser>();
        query.addWhereEqualTo(key, value);
        query.findObjects(context, new FindListener<BmobUser>() {
            @Override
            public void onSuccess(List<BmobUser> list) {
                if (list.isEmpty()) {
                    mState.getState(MODE_SEARCH_USER, false);
                } else {
                    mState.getState(MODE_SEARCH_USER, true);
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    public static void resetPwd(Context context, String value) {
        User user = new User();
        user.setPassword(value);
        user.update(context, "b7ff9b5081", new UpdateListener() {
            @Override
            public void onSuccess() {
                mState.getState(MODEL_RESET_PWD, true);
                Log.i("bluedai", "reset successful");
            }

            @Override
            public void onFailure(int i, String s) {

            }
        });

    }
}
