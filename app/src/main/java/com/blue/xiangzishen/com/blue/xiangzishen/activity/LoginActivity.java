package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.bean.User;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.AccountManager;
import com.blue.xiangzishen.com.blue.xiangzishen.utils.Utils;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by blue on 16-3-31.
 */
public class LoginActivity extends Activity {

    private Button mSignButton, mLoginButton;
    private EditText mUserEdit, mPwdEdit;
    private String mUserText, mPwdText;
    private User mUser;
    private int userflag, pwdflag;
    private String userTextFlag, pwdTextFlag;
    public static final String TAG = LoginActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUser = new User();
        initView();
    }

    private void initView() {
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mLoginButton.setEnabled(false);
        mSignButton = (Button) findViewById(R.id.btn_sign);
        mUserEdit = (EditText) findViewById(R.id.et_username);
        mPwdEdit = (EditText) findViewById(R.id.et_password);
        listenEdit(mUserEdit);
        listenEdit(mPwdEdit);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEdit();
                AccountManager.login(LoginActivity.this, mUserText, mPwdText);
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

    private void listenEdit(final EditText edit) {

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (edit.getId()) {
                    case R.id.et_username:
                        break;
                    case R.id.et_password:
                        break;
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (edit.getId()) {
                    case R.id.et_username:
                        userflag = charSequence.length();
                        setEnable();
                        break;
                    case R.id.et_password:
                        pwdflag = charSequence.length();
                        setEnable();
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                switch (edit.getId()) {
                    case R.id.et_username:
                        userTextFlag = editable.toString();
                        break;
                    case R.id.et_password:
                        pwdTextFlag = editable.toString();
                        break;
                }
            }
        });
    }

    private void setEnable() {
        if (pwdflag != 0 && userflag != 0) {
            mLoginButton.setEnabled(true);
        } else {
            mLoginButton.setEnabled(false);
        }
    }
    private void getEdit() {
        mUserText = mUserEdit.getText().toString();
        mPwdText = mPwdEdit.getText().toString();
    }
}
