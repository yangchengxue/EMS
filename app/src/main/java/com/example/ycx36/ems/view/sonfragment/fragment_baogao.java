package com.example.ycx36.ems.view.sonfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ycx36.ems.R;

import butterknife.ButterKnife;

public class fragment_baogao extends Fragment {
    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.layout_f_baogao, container, false);
            ButterKnife.bind(this,view);
        }
        return view;
    }
}
