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

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

/**
 * Created by blue on 16-4-4.
 */
public class CheckSMSActivity extends Activity {
    private TextView mNumber, mTime;
    private Button mVerify;
    private EditText mCode;
    private String mPhoneNamber, mCodeText;
    private CountDownTimer mTimer;
    public static final String TAG = CheckSMSActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checksms);
        getNumber();
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

    private void getNumber() {
        mPhoneNamber = getIntent().getStringExtra("number");
    }

    private void checkCode() {
        BmobSMS.verifySmsCode(this, mPhoneNamber, mCodeText, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Log.i(TAG, "verify success");
                    startActivity(new Intent(CheckSMSActivity.this, MainActivity.class));
                } else {
                    Log.i(TAG, "verify failed " + e);
                }
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
