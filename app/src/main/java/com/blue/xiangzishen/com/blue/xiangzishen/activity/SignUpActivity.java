package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.bean.User;

import cn.bmob.v3.listener.SaveListener;

/**
 * Created by blue on 16-3-31.
 */
public class SignUpActivity extends Activity {
    private Button mSignButton;
    private EditText mUserName, mPwd, mConfrimPwd;
    private String mUserNameText, mPwdText, mConfirmPwdText;
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
        mConfrimPwd = (EditText) findViewById(R.id.et_confirm_pwd);
        mSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEdit();
            }
        });
    }

    private void getEdit() {
        mUserNameText = mUserName.getText().toString();
        mPwdText = mPwd.getText().toString();
        mConfirmPwdText = mConfrimPwd.getText().toString();
        mUser.setName(mUserNameText);
        mUser.setPwd(mPwdText);
        mUser.save(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(), "Success :" + mUser.getObjectId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(getApplicationContext(), "failed :" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
