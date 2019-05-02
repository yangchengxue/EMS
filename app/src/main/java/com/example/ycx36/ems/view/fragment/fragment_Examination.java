package com.example.ycx36.ems.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ycx36.ems.R;
import com.example.ycx36.ems.view.activity.ExaminationActivity;
import com.github.lzyzsd.circleprogress.ArcProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class fragment_Examination extends Fragment {

    @BindView(R.id.arc_progress) ArcProgress arcProgressBar;
    @BindView(R.id.bt_examination) Button bt_examination;

    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_examination, container, false);
            ButterKnife.bind(this,view);

        }
        return view;
    }



    @OnClick({R.id.arc_progress,R.id.bt_examination,R.id.l1,R.id.l2,R.id.l3,R.id.l4,R.id.l5,R.id.l6})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arc_progress: //点击
                break;
            case R.id.bt_examination: //点击开始体检（已隐藏）
                break;
            case R.id.l1:
                Intent intent = new Intent(getActivity(),ExaminationActivity.class);
                startActivity(intent);
                break;
            case R.id.l2:
                break;
            case R.id.l3:
                break;
            case R.id.l4:
                break;
            case R.id.l5:
                break;
            case R.id.l6:
                break;
        }
    }

    int status = 0;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (msg.what == 0x111){
                arcProgressBar.setProgress(status);
            }
            if (msg.what == 1){
                bt_examination.setText("体检完成");
            }
        }
    };



}
