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

    public static void sendRequestCode(final Context context, String phone) {
        BmobSMS.requestSMSCode(context, phone, "注册", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                if (e == null) {
                    Log.i(TAG, "SMS id : " + integer);
                    Toast.makeText(context.getApplicationContext(), "Send SMS code successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    public static void checkCode(Context context, String phone, String code){
//        BmobSMS.verifySmsCode(context, phone, code, new VerifySMSCodeListener() {
//            @Override
//            public void done(BmobException e) {
//                if (e == null) {
//                    sign();
//                } else {
//                    mTimer.onFinish();
//                    Toast.makeText(getApplicationContext(), "The request code is wrong", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}
