package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blue.xiangzishen.R;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.VerifySMSCodeListener;

/**
 * Created by blue on 16-4-4.
 */
public class CheckSMSActivity extends Activity {
    private TextView mNumber, mTime;
    private Button mVerify;
    private EditText mCode;
    private String mPhoneNamber, mCodeText;
    public static final String TAG = CheckSMSActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checksms);
        getNumber();
        initView();
    }

    private void initView() {
        mNumber = (TextView) findViewById(R.id.tv_number);
        mTime = (TextView) findViewById(R.id.tv_time);
        mVerify = (Button) findViewById(R.id.btn_verification);
        mCode = (EditText) findViewById(R.id.et_code);
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
}
