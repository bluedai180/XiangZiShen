package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.bean.User;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by blue on 16-3-31.
 */
public class SignUpActivity extends Activity {
    private Button mSignButton;
    private EditText mUserName, mPwd, mNumber;
    private String mUserNameText, mPwdText, mNumberText;
    private static final String TAG = SignUpActivity.class.getName();
    User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mUser = new User();
        initView();
    }

    private void initView() {
        mSignButton = (Button) findViewById(R.id.btn_signup);
        mUserName = (EditText) findViewById(R.id.et_sign_username);
        mPwd = (EditText) findViewById(R.id.et_sign_pwd);
        mNumber = (EditText) findViewById(R.id.et_number);
        mSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEdit();
                sendRequestCode();
                checkSMSCode();
            }
        });
    }

    private void getEdit() {
        mUserNameText = mUserName.getText().toString();
        mPwdText = mPwd.getText().toString();
        mNumberText = mNumber.getText().toString();
        mUser.setName(mUserNameText);
        mUser.setPwd(mPwdText);
        mUser.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
            }

            @Override
            public void onFailure(int i, String s) {
            }
        });
    }

    private void sendRequestCode() {
        BmobSMS.requestSMSCode(this, mNumberText, "注册", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    Log.i(TAG, "SMS id : " + integer);
                }
            }
        });
    }

    private void checkSMSCode() {
        Intent intent = new Intent(SignUpActivity.this, CheckSMSActivity.class);
        intent.putExtra("number", mNumberText);
        startActivity(intent);
    }
}
