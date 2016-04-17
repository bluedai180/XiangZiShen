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
import com.blue.xiangzishen.com.blue.xiangzishen.manager.SMSManager;
import com.blue.xiangzishen.com.blue.xiangzishen.utils.Utils;

import java.util.List;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by blue on 16-3-31.
 */
public class SignUpActivity extends Activity {
    private Button mSignButton;
    private EditText mUserName, mPwd, mNumber;
    private String mUserNameText, mPwdText, mNumberText;
    private int userflag, pwdflag, numberflag;
    private static final String TAG = SignUpActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }

    private void initView() {
        mSignButton = (Button) findViewById(R.id.btn_signup);
        mSignButton.setEnabled(false);
        mUserName = (EditText) findViewById(R.id.et_sign_username);
        mPwd = (EditText) findViewById(R.id.et_sign_pwd);
        mNumber = (EditText) findViewById(R.id.et_number);
        listenEdit(mUserName);
        listenEdit(mPwd);
        listenEdit(mNumber);

        mSignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEdit();
                sendRequestSMSCode();
            }
        });
    }

    private void listenEdit(final EditText edit) {

        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (edit.getId()) {
                    case R.id.et_sign_username:
                        break;
                    case R.id.et_sign_pwd:
                        break;
                    case R.id.et_number:
                        break;
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switch (edit.getId()) {
                    case R.id.et_sign_username:
                        userflag = charSequence.length();
                        setEnable();
                        break;
                    case R.id.et_number:
                        numberflag = charSequence.length();
                        setEnable();
                        break;
                    case R.id.et_sign_pwd:
                        pwdflag = charSequence.length();
                        setEnable();
                        break;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

                switch (edit.getId()) {
                    case R.id.et_sign_username:
                        break;
                    case R.id.et_sign_pwd:
                        break;
                    case R.id.et_number:
                        break;
                }
            }
        });
    }

    private void setEnable() {
        if (numberflag != 0 && pwdflag != 0 && userflag != 0) {
            mSignButton.setEnabled(true);
        } else {
            mSignButton.setEnabled(false);
        }
    }

    private void getEdit() {
        mUserNameText = mUserName.getText().toString();
        mPwdText = mPwd.getText().toString();
        mNumberText = mNumber.getText().toString();
    }

    private void sendRequestSMSCode() {
        if (Utils.isPhoneNumber(mNumberText)) {
            Intent intent = new Intent(SignUpActivity.this, CheckSMSActivity.class);
            intent.putExtra("name", mUserNameText);
            intent.putExtra("password", mPwdText);
            intent.putExtra("phone", mNumberText);
            SMSManager.sendRequestCode(SignUpActivity.this, mNumberText);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Please input right phone number", Toast.LENGTH_SHORT).show();
        }
    }
}
