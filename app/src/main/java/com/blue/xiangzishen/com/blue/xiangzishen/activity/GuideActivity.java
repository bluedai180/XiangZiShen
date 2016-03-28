package com.blue.xiangzishen.com.blue.xiangzishen.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blue.xiangzishen.R;
import com.blue.xiangzishen.com.blue.xiangzishen.adapter.GuideAdapter;

import java.util.ArrayList;

/**
 * Created by blue on 16-3-28.
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private GuideAdapter mGuideAdapter;
    private static int[] mImages = {R.drawable.guide_image1, R.drawable.guide_image2, R.drawable.guide_image3};
    private ArrayList<ImageView> mImage_list;
    private ImageView[] mDotVIews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);

        initImages();
        mGuideAdapter = new GuideAdapter(mImage_list);
        mViewPager.setAdapter(mGuideAdapter);
        mViewPager.setOnPageChangeListener(this);
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
                        Toast.makeText(GuideActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
//    private void initDotView(){
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_dot);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        layoutParams.setMargins(10, 0, 10, 0);
//        mDotVIews = new ImageView[mImages.length];
//        for (int i = 0; i > mImages.length; i++){
//            ImageView imageView = new ImageView(this);
//        }
//
//    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
