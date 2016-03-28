package com.blue.xiangzishen.com.blue.xiangzishen.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by blue on 16-3-28.
 */
public class GuideAdapter extends PagerAdapter {
    private ArrayList<ImageView> mImageList;

    public GuideAdapter(ArrayList<ImageView> imageViewArrayList) {
        this.mImageList = imageViewArrayList;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageList.get(position));
        return mImageList.get(position);
    }
}
