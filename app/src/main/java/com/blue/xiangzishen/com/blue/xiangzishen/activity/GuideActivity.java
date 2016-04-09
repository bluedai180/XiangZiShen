package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.adapter.GuideAdapter;
import com.blue.xiangzishen.com.blue.xiangzishen.bean.User;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.AccountManager;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.SearchUserListener;
import com.blue.xiangzishen.com.blue.xiangzishen.manager.StateListener;
import com.blue.xiangzishen.com.blue.xiangzishen.utils.Utils;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;

/**
 * Created by blue on 16-3-28.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener, SearchUserListener {

    private ViewPager mViewPager;
    private GuideAdapter mGuideAdapter;
    private Button mLoginButton, mSignoutButton;
    private static int[] mImages = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private ArrayList<ImageView> mImage_list;
    private ImageView[] mDotVIews;
    AccountManager mAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        Bmob.initialize(this, Utils.BMOB_APP_KEY);
        initButton();
        initImages();
        initDotView();
        mAccount = new AccountManager();
        mAccount.setUserListener(this);
        AccountManager.getCurrentUser(GuideActivity.this);

        mGuideAdapter = new GuideAdapter(mImage_list);
        mViewPager.setAdapter(mGuideAdapter);
        mViewPager.setOnPageChangeListener(this);
    }

    private void initButton() {
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mSignoutButton = (Button) findViewById(R.id.btn_regist);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        mSignoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initImages() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mImage_list = new ArrayList<ImageView>();
        for (int i = 0; i < mImages.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(mImages[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            mImage_list.add(imageView);
            if (i == mImages.length - 1) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    private void initDotView() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_dot);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 0, 10, 0);
        mDotVIews = new ImageView[mImages.length];

        for (int i = 0; i < mImages.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageResource(R.drawable.guide_point);
            if (i == 0) {
                imageView.setSelected(true);
            } else {
                imageView.setSelected(false);
            }
            mDotVIews[i] = imageView;
            linearLayout.addView(imageView);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < mDotVIews.length; i++) {
            if (position == i) {
                mDotVIews[i].setSelected(true);
            } else {
                mDotVIews[i].setSelected(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void getUser(User user) {
        if (user != null) {
            String name = user.getUsername();
            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
            //startActivity(intent);
        } else {
        }
    }
}
