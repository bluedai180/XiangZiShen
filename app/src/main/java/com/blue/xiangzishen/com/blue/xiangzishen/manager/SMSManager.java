package com.blue.xiangzishen.com.blue.xiangzishen.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

/**
 * Created by blue on 16-4-7.
 */
public class SMSManager {

    public static final String TAG = SMSManager.class.getName();
    public static final String MODEL_SEND_SMS_CODE = "send_code";
    public static final String MODEL_CHECK_SMS_CODE = "verify_code";
    static StateListener mState;

    public void setListener(StateListener state) {
        this.mState = state;
    }

    public static void sendRequestCode(final Context context, String phone) {
        BmobSMS.requestSMSCode(context, phone, "注册", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    mState.getState(MODEL_SEND_SMS_CODE, true);
                }
            }
        });
    }

    public static void checkCode(Context context, String phone, String code) {
        BmobSMS.verifySmsCode(context, phone, code, new VerifySMSCodeListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    mState.getState(MODEL_CHECK_SMS_CODE, true);
                } else {
                    mState.getState(MODEL_CHECK_SMS_CODE, false);
                }
            }
        });
    }
}
