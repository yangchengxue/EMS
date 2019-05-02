package com.example.ycx36.ems.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ycx36.ems.R;
import com.example.ycx36.ems.view.activity.activity_fromDiscover;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class fragment_Discover extends Fragment {

    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_discover, container, false);
            ButterKnife.bind(this,view);
        }
        return view;
    }

    @OnClick({R.id.RL_1,R.id.RL_2,R.id.RL_3,R.id.RL_4,R.id.RL_5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.RL_1:
                toActivity("安全故障历史");
                break;
            case R.id.RL_2:
                toActivity("故障趋势分析");
                break;
            case R.id.RL_3:
                toActivity("安全历史事件");
                break;
            case R.id.RL_4:
                toActivity("车辆历史分析");
                break;
            case R.id.RL_5:
                toActivity("安全事件处理方案");
                break;
        }
    }

    public void toActivity(String s){
        Intent intent = new Intent(getActivity(),activity_fromDiscover.class);
        Bundle bundle = new Bundle();      //创建一个budle对象
        bundle.putString("title", s);  //写入数据
        intent.putExtras(bundle);             //用putExtras方法将写入的
        startActivity(intent);
    }

}
