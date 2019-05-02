package com.example.ycx36.ems.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.example.ycx36.ems.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_fromDiscover extends AppCompatActivity {

    @BindView(R.id.tv_header_centerText)
    TextView tv_header_centerText;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_discover);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        title = bundle.getString("title");
        tv_header_centerText.setText(title);

    }

    @OnClick({R.id.tv_header_back,R.id.RL_1,R.id.RL_2,R.id.RL_3,R.id.RL_4,R.id.RL_5})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_header_back:
                finish();
                break;
            case R.id.RL_1:
                toActivity(title);
                break;
            case R.id.RL_2:
                toActivity(title);
                break;
            case R.id.RL_3:
                toActivity(title);
                break;
            case R.id.RL_4:
                toActivity(title);
                break;
            case R.id.RL_5:
                toActivity(title);
                break;
        }
    }

    public void toActivity(String s){
        Intent intent = new Intent(activity_fromDiscover.this,activity_CauseOfFailure.class);
        Bundle bundle = new Bundle();      //创建一个budle对象
        bundle.putString("title", s);  //写入数据
        intent.putExtras(bundle);             //用putExtras方法将写入的
        startActivity(intent);
    }
}
