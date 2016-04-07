package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.bean.User;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by blue on 16-4-4.
 */
public class CheckSMSActivity extends Activity {
    private TextView mNumber, mTime;
    private Button mVerify;
    private EditText mCode;
    private String mUserText, mPwdText, mPhoneNamber, mCodeText;
    private CountDownTimer mTimer;
    private User mUser;
    public static final String TAG = CheckSMSActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checksms);
        mUser = new User();
        getInfo();
        initView();
        countdown();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    private void initView() {
        mNumber = (TextView) findViewById(R.id.tv_number);
        mTime = (TextView) findViewById(R.id.tv_time);
        mVerify = (Button) findViewById(R.id.btn_verification);
        mVerify.setEnabled(false);
        mCode = (EditText) findViewById(R.id.et_code);
        mCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() != 0) {
                    mVerify.setEnabled(true);
                } else {
                    mVerify.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mNumber.setText(mPhoneNamber);
        mVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeText = mCode.getText().toString();
                checkCode();
            }
        });
    }

    private void getInfo() {
        mUserText = getIntent().getStringExtra("name");
        mPwdText = getIntent().getStringExtra("password");
        mPhoneNamber = getIntent().getStringExtra("phone");
        Log.i("bluedai", mUserText + mPwdText + mPhoneNamber);
    }

    private void checkCode() {
        BmobSMS.verifySmsCode(this, mPhoneNamber, mCodeText, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    sign();
                } else {
                    mTimer.onFinish();
                    Toast.makeText(getApplicationContext(), "The request code is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sign() {
        Log.i("bluedai", "sign" + mUserText + mPwdText + mPhoneNamber);
        mUser.setUsername(mUserText);
        mUser.setPassword(mPwdText);
        mUser.setMobilePhoneNumber(mPhoneNamber);
        mUser.setMobilePhoneNumberVerified(true);
        mUser.signUp(this, new SaveListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void countdown() {
        mTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long l) {
                mTime.setText("Your SMS should arrive in " + l / 1000 + "second");
            }

            @Override
            public void onFinish() {
                mTime.setText("Resend SMS code");
                mTime.setEnabled(true);
                mTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resendRequestCode();
                    }
                });
            }
        };
        mTimer.start();
    }

    private void resendRequestCode() {
        BmobSMS.requestSMSCode(this, mPhoneNamber, "注册", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    Log.i(TAG, "resend successful, SMS id : " + integer);
                    Toast.makeText(CheckSMSActivity.this, "send SMS code successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
