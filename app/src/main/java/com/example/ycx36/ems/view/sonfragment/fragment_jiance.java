package com.example.ycx36.ems.view.sonfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.ycx36.ems.R;
import com.example.ycx36.ems.view.activity.activity_CurrentInformation;
import com.github.lzyzsd.circleprogress.CircleProgress;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

public class fragment_jiance extends Fragment {

    @BindView(R.id.circle_progress) CircleProgress circle_progress;
    @BindView(R.id.text_tipStatus) TextView text_tipStatus;
    @BindView(R.id.progressBar11) ProgressBar progressBar11;
    @BindView(R.id.progressBar12) ProgressBar progressBar12;
    @BindView(R.id.progressBar13) ProgressBar progressBar13;
    @BindView(R.id.progressBar14) ProgressBar progressBar14;
    @BindView(R.id.d1) TextView d1;
    @BindView(R.id.d2) TextView d2;
    @BindView(R.id.d3) TextView d3;
    @BindView(R.id.d4) TextView d4;
    @BindView(R.id.d5) TextView d5;
    @BindView(R.id.d6) TextView d6;
    @BindView(R.id.d7) TextView d7;

    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.layout_f_jiance, container, false);
            ButterKnife.bind(this,view);
            startExamination();
        }
        return view;
    }

    int status = 0;
    int flag = 0,flag2 = 0;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @SuppressLint({"ResourceAsColor", "SetTextI18n"})
        public void handleMessage(Message msg){
            if (msg.what == 0x111){
                if (flag == 0){
                    flag = 1;
                    d2.setTextColor(getResources().getColor(R.color.gray3));
                }
                d2.setText("检测中…"+status+"%");
                circle_progress.setProgress(status);
//                progressBar11.setProgress(status);
                progressBar12.setProgress(status);
//                progressBar13.setProgress(status);
//                progressBar14.setProgress(status);
            }
            if (msg.what == 1){
                text_tipStatus.setText("检测完成");
                d2.setText("安全（点击查看详情）");
                llflag2 = 1;
            }
        }
    };

    public void startExamination(){
        new Thread(){
            public void run(){
                while (status<100){
                    status++;
                    //模拟耗时操作
                    try
                    {
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0x111);
                    if (status==100){
                        handler.sendEmptyMessage(1);
                    }
                }
            }
        }.start();
    }

    int llflag1 = 0,llflag2 = 0;
    @OnClick({R.id.ll1,R.id.ll2,R.id.ll3,R.id.ll4,R.id.ll5,R.id.ll6,R.id.ll7})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll1:
                break;
            case R.id.ll2:   //mcu电机
                if (llflag2 == 1){
                    startActivity(new Intent(getActivity(), activity_CurrentInformation.class));
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("ExaminationFraction",MODE_PRIVATE).edit();
                    editor.putInt("muc",5);
                    editor.apply();
                }
                break;
            case R.id.ll3:
                break;
            case R.id.ll4:
                break;
            case R.id.ll5:
                break;
            case R.id.ll6:
                break;
            case R.id.ll7:
                break;
        }
    }
}
