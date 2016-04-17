package com.blue.xiangzishen.com.blue.xiangzishen.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;

/**
 * Created by blue on 16-4-6.
 */
public class Utils {
    // Bmob key.
    public static final String BMOB_APP_KEY = "157c6001fbee42fee6e1ff9f32444f9a";
    // Phone number regular expression.
    public static final String NUMBER_EXPRESSION = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    // check if the phone number
    public static boolean isPhoneNumber(String number) {
        Pattern p = Pattern.compile(NUMBER_EXPRESSION);
        Matcher m = p.matcher(number);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
