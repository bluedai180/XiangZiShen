package com.blue.xiangzishen.com.blue.xiangzishen.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.blue.xiangzishen.R;

/**
 * Created by blue on 16-3-27.
 */
public class HomeFragment extends Fragment {
    private FloatingActionButton mFloatingButtion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mFloatingButtion = (FloatingActionButton) view.findViewById(R.id.btn_floatting);
        mFloatingButtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "floatingbutton", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
