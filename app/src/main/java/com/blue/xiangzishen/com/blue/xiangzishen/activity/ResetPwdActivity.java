package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.AccountManager;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.SMSManager;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.StateListener;

/**
 * Created by blue on 16-4-11.
 *
 * If check code is successful, then reset password.
 */
public class ResetPwdActivity extends Activity implements StateListener {
    private EditText mNewPwd, mConfirmPwd, mCode;
    private Button mReset;
    SMSManager mSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        mSMS = new SMSManager();
        mSMS.setListener(this);
        initView();
    }

    private void initView() {
        mReset = (Button) findViewById(R.id.btn_reset);
        mNewPwd = (EditText) findViewById(R.id.et_new_pwd);
        mConfirmPwd = (EditText) findViewById(R.id.et_confirm_pwd);
        mCode = (EditText) findViewById(R.id.et_reset_code);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SMSManager.resetPassword(ResetPwdActivity.this, mCode.getText().toString(), mConfirmPwd.getText().toString());
            }
        });
    }

    @Override
    public void getState(String mode, boolean isSuccessful) {
        switch (mode) {
            case SMSManager.MODEL_CHECK_FORGET_CODE:
                if (isSuccessful) {
                    Toast.makeText(getApplicationContext(), "Change password successful!", Toast.LENGTH_SHORT).show();
                }
                break;
            case SMSManager.MODEL_SEND_SMS_CODE:
                if (isSuccessful) {
                    Toast.makeText(getApplicationContext(), "Send code successful", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
