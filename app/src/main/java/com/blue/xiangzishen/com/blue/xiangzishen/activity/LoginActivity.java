package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.bean.User;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by blue on 16-3-31.
 */
public class LoginActivity extends Activity {
    public static final String KEY = "157c6001fbee42fee6e1ff9f32444f9a";
    private Button mSignButton, mLoginButton;
    private EditText mUserEdit, mPwdEdit;
    private String mUserText, mPwdText;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, KEY);
        mUser = new User();
        initView();
    }

    private void initView() {
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mSignButton = (Button) findViewById(R.id.btn_sign);
        mUserEdit = (EditText) findViewById(R.id.et_username);
        mPwdEdit = (EditText) findViewById(R.id.et_password);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEdit();
                isLogin();
            }
        });
        mSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getEdit() {
        mUserText = mUserEdit.getText().toString();
        mPwdText = mPwdEdit.getText().toString();
    }

    private void isLogin() {
        BmobQuery<User> query = new BmobQuery<User>();
        query.addWhereContains("username", mUserText);
        query.addWhereContains("password", mPwdText);
        query.findObjects(this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                Toast.makeText(getApplicationContext(), "Log in successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(getApplicationContext(), "Wrong info" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
