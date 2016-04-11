package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.AccountManager;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.StateListener;

/**
 * Created by blue on 16-4-11.
 */
public class ResetPwdActivity extends Activity implements StateListener {
    private EditText mNewPwd, mConfirmPwd;
    private Button mReset;
    AccountManager mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        mAccount = new AccountManager();
        mAccount.setListener(this);
        initView();
    }

    private void initView() {
        mReset = (Button) findViewById(R.id.btn_reset);
        mNewPwd = (EditText) findViewById(R.id.et_new_pwd);
        mConfirmPwd = (EditText) findViewById(R.id.et_confirm_pwd);
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountManager.resetPwd(ResetPwdActivity.this, mConfirmPwd.getText().toString());
            }
        });
    }

    @Override
    public void getState(String mode, boolean isSuccessful) {
        if (mode == AccountManager.MODEL_RESET_PWD) {
            if (isSuccessful) {
                Toast.makeText(getApplicationContext(), "Reset password successful", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
