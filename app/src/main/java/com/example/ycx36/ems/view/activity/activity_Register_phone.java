package com.example.ycx36.ems.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.example.ycx36.ems.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_Register_phone extends AppCompatActivity {

    @BindView(R.id.phNumber) EditText phNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_header_back,R.id.getVcode})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回
                finish();
                break;
            case R.id.getVcode:
                AVOSCloud.requestSMSCodeInBackground(phNumber.getText().toString(), new RequestMobileCodeCallback() {
                    @Override
                    public void done(AVException e) {
                        Toast.makeText(activity_Register_phone.this, "发送失败 "+e.getCode(), Toast.LENGTH_SHORT).show();
                    }
                });
                Intent intent = new Intent(activity_Register_phone.this,activity_Register_Vcode.class);
                Bundle bundle = new Bundle();      //创建一个budle对象
                bundle.putString("phone", phNumber.getText().toString());  //写入数据
                intent.putExtras(bundle);             //用putExtras方法将写入的数据存入intent中
                startActivity(intent);
                break;
        }
    }
}
