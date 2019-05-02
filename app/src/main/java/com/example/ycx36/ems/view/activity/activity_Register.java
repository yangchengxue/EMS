package com.example.ycx36.ems.view.activity;

import android.content.SharedPreferences;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.example.ycx36.ems.R;
import com.example.ycx36.ems.util.interface_retrofit;
import com.example.ycx36.ems.util.registerBean;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class activity_Register extends AppCompatActivity {

    @BindView(R.id.et_userName) EditText et_userName;
    @BindView(R.id.et_userPassword) EditText et_userPassword;
    @BindView(R.id.et_userCPassword) EditText et_userCPassword;
    @BindView(R.id.et_userPhone) EditText et_userPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.tv_header_back,R.id.bt_Register})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回
                finish();
                break;
            case R.id.bt_Register:
//                RegistrationUser(et_userName,et_userPassword,et_userPhone);
                RegistrationUser(et_userName,et_userPhone,et_userPassword,et_userCPassword);
                break;
        }
    }

    public void RegistrationUser(EditText et_userName, EditText et_userPhone,EditText et_userPassword, EditText et_userCPassword) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tbox.techroom.com.cn/")//基础URL 建议以 / 结尾
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                .build();
        interface_retrofit rxjavaService = retrofit.create(interface_retrofit.class);
        if (et_userPhone.getText().toString().length() == 11){
            rxjavaService.register(et_userName.getText().toString(),et_userPhone.getText().toString(),et_userPassword.getText().toString(),et_userCPassword.getText().toString())
                    .subscribeOn(Schedulers.io())//IO线程加载数据
                    .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                    .subscribe(new Subscriber<registerBean>() {
                        @Override
                        public void onCompleted() {
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (e.getMessage() == "HTTP 401 Unauthorized"){
                                Toast.makeText(activity_Register.this,"手机号已被注册。",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(activity_Register.this,"错误信息："+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNext(registerBean data) {
                            Toast.makeText(activity_Register.this,"注册成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
        }else {
            Toast.makeText(activity_Register.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
        }

    }
}
