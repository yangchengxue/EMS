package com.example.ycx36.ems.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ycx36.ems.R;
import com.example.ycx36.ems.recyclerview.adapter_CurrentInfo;
import com.example.ycx36.ems.recyclerview.muc_data;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_CurrentInformation extends AppCompatActivity {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    private ArrayList<muc_data> mucData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__current_information);
        ButterKnife.bind(this);
        initdata();

/**获取一个线性布局管理器（设置为竖直模式显示的时候用这个管理器）*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);  //将布局管理器设置到recyclerView中
        recyclerView.setItemAnimator(new DefaultItemAnimator());  //调用系统默认的删除增加item的动画
        final adapter_CurrentInfo adapter = new adapter_CurrentInfo(mucData); //获取适配器实例
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));  //调用系统横向分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));  //调用系统纵向分割线
        /**条目点击事件*/
        adapter.setOnItemClickListener(new adapter_CurrentInfo.OnItemClickListener() {
            @Override
            public void setOnItemClickListener(View view, int position) {
                switch (position){
                    case 0:
                        break;
                }
            }
        });
        /**条目长按事件*/
        adapter.setOnLongClickListener(new adapter_CurrentInfo.OnLongClickListener() {
            @Override
            public void setOnLongClickListener(View view, int position) {
                /**删除条目的方法*/
                adapter.removeData(position);
                //Toast.makeText(getActivity(),"这是条目"+position+1,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initdata(){
        String motorspeed = getSharedPreferences("MOTORSPEED",MODE_PRIVATE).getString("motorspeed","未获取到");
        String motortorque = getSharedPreferences("MOTOTTORQUE",MODE_PRIVATE).getString("motortorque","未获取到");
        String totalcurrent = getSharedPreferences("TOTALCURRENT",MODE_PRIVATE).getString("totalcurrent","未获取到");
        String totalvoltage = getSharedPreferences("TOTALVOLTAGE",MODE_PRIVATE).getString("totalvoltage","未获取到");
        muc_data data1 = new muc_data("电机转速",motorspeed,"-15000-15000");
        muc_data data2 = new muc_data("电机转矩",motortorque,"-5000-5000");
        muc_data data3 = new muc_data("电机电流",totalcurrent,"1：正常 0：不正常");
        muc_data data4 = new muc_data("电机电压",totalvoltage,"1：正常 0：不正常");
        mucData.add(data1);
        mucData.add(data2);
        mucData.add(data3);
        mucData.add(data4);
    }

    @OnClick({R.id.lin_header_back2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_header_back2:
                finish();
                break;
        }
    }
}
