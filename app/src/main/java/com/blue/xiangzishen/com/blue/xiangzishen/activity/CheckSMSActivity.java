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
import com.blue.xiangzishen.com.blue.xiangzishen.manager.AccountManager;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.SMSManager;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.StateListener;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by blue on 16-4-4.
 */
public class CheckSMSActivity extends Activity implements StateListener {
    private TextView mNumber, mTime;
    private Button mVerify;
    private EditText mCode;
    private String mUserText, mPwdText, mPhoneNamber, mCodeText;
    private CountDownTimer mTimer;
    private User mUser;
    AccountManager mAccount;
    SMSManager mSMS;
    public static final String TAG = CheckSMSActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checksms);
        mUser = new User();
        mAccount = new AccountManager();
        mSMS = new SMSManager();
        mAccount.setListener(this);
        mSMS.setListener(this);
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
                SMSManager.checkCode(CheckSMSActivity.this, mPhoneNamber, mCodeText);
            }
        });
    }

    private void getInfo() {
        mUserText = getIntent().getStringExtra("name");
        mPwdText = getIntent().getStringExtra("password");
        mPhoneNamber = getIntent().getStringExtra("phone");
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
                        SMSManager.sendRequestCode(CheckSMSActivity.this, mPhoneNamber);
                    }
                });
            }
        };
        mTimer.start();
    }

    @Override
    public void getState(String mode, boolean successful) {
        switch (mode) {
            case SMSManager.MODEL_CHECK_SMS_CODE:
                if (successful) {
                    AccountManager.sign(this, mUserText, mPwdText, mPhoneNamber);
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                } else {
                    mTimer.onFinish();
                    Toast.makeText(getApplicationContext(), "The code is wrong", Toast.LENGTH_SHORT).show();
                }
                break;
            case SMSManager.MODEL_SEND_SMS_CODE:
                if (successful) {
                    Toast.makeText(getApplicationContext(), "Send SMS code successful", Toast.LENGTH_SHORT).show();
                }
                break;
            case AccountManager.MODE_SIGN:
                if (successful) {
                    Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "The phone number has already signed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
