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

public class activity_CauseOfFailure extends AppCompatActivity {

    @BindView(R.id.tv_header_centerText) TextView tv_header_centerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__cause_of_failure);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        tv_header_centerText.setText(bundle.getString("title"));
    }

    @OnClick({R.id.tv_header_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_header_back:
                finish();
                break;
        }
    }

}
