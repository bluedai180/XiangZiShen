package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blue.xiangzishen.R;

import cn.bmob.v3.Bmob;

/**
 * Created by blue on 16-3-31.
 */
public class LoginActivity extends Activity {
    public static final String KEY = "157c6001fbee42fee6e1ff9f32444f9a";
    private Button mSignButton, mLoginButton;
    private EditText mUserEdit, mPwdEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bmob.initialize(this, KEY);
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
}
