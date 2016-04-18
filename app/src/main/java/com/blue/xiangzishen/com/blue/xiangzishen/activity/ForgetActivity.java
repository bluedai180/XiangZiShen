package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.AccountManager;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.SMSManager;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.StateListener;

/**
 * Created by blue on 16-4-10.
 *
 * Forget the password and resign.
 */
public class ForgetActivity extends Activity implements StateListener {
    private EditText mPhoneNumber;
    private TextInputLayout mTextInputLayout;
    private Button mButton;
    AccountManager mAccount;
    SMSManager mSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        mAccount = new AccountManager();
        mSMS = new SMSManager();
        mAccount.setListener(this);
        mSMS.setListener(this);
        initView();
    }

    private void initView() {
        mPhoneNumber = (EditText) findViewById(R.id.et_forget);
        mTextInputLayout = (TextInputLayout) findViewById(R.id.tl_forget);
        mButton = (Button) findViewById(R.id.btn_forget);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountManager.searchUser(ForgetActivity.this, "mobilePhoneNumber", mPhoneNumber.getText().toString());
            }
        });
    }

    @Override
    public void getState(String mode, boolean isSuccessful) {
        switch (mode) {
            case AccountManager.MODE_SEARCH_USER:
                if (isSuccessful) {
                    Intent intent = new Intent(ForgetActivity.this, ResetPwdActivity.class);
                    startActivity(intent);
                    SMSManager.sendRequestCode(this, mPhoneNumber.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "This phone number is not exist", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
