package com.example.ycx36.ems.view.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.example.ycx36.ems.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class activity_Register_Vcode extends AppCompatActivity {

    @BindView(R.id.Vcode) AutoCompleteTextView Vcode;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register__vcode);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();    //bundle取出之前存在intent的数据
        phone = bundle.getString("phone");   //根据键名读取数据
    }

    @OnClick({R.id.tv_header_back,R.id.bt_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回
                finish();
                break;
            case R.id.bt_next:
                AVUser.signUpOrLoginByMobilePhoneInBackground(phone, Vcode.getText().toString(), new LogInCallback<AVUser>() {
                    @Override
                    public void done(AVUser avUser, AVException e) {
                        // 如果 e 为空就可以表示登录成功了，并且 user 是一个全新的用户
                        if (e == null){
                            Toast.makeText(activity_Register_Vcode.this, "手机号注册成功", Toast.LENGTH_SHORT).show();
                           // startActivity(new Intent(activity_Register_Vcode.this,activity_Register.class));
                        }
                    }
                });
                break;
        }
    }
}
