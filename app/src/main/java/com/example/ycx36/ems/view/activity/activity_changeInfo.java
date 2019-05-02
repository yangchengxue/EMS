package com.example.ycx36.ems.view.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ycx36.ems.R;
import com.example.ycx36.ems.util.changeUserInfoBean;
import com.example.ycx36.ems.util.hw_checkBean;
import com.example.ycx36.ems.util.interface_retrofit;
import com.example.ycx36.ems.util.registerBean;
import com.example.ycx36.ems.util.userInfoBean;

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

public class activity_changeInfo extends AppCompatActivity {

    @BindView(R.id.tv_userName) EditText tv_userName;
    @BindView(R.id.tv_email) EditText tv_email;
    @BindView(R.id.tv_mobilephone) EditText tv_mobilephone;

    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        ButterKnife.bind(this);

        SharedPreferences pref = getSharedPreferences("TokenInfo",MODE_PRIVATE);
        token = pref.getString("token","");
        Retrofit retrofit2 = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())  //Gson解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://tbox.techroom.com.cn/")  //数据接口的主地址
                .build();
        interface_retrofit getBDPhotosRequest_interface = retrofit2.create(interface_retrofit.class);
        if (!token.equals("")) {
            getBDPhotosRequest_interface.getUserInfo("Bearer " + token)
                    .subscribeOn(Schedulers.io())  //观察者切换新线程,subscribe只能调用一次。
                    .doOnNext(new Action1<userInfoBean>() {  //请求结束后调用 doOnNext(),并获data数据
                        @Override
                        public void call(userInfoBean datas) {


                        }
                    })
                    .observeOn(AndroidSchedulers.mainThread())   //被观察者切换到主线程
                    .subscribe(new Action1<userInfoBean>() {    //观察者监听到datas数据,主线程中执行
                        @Override
                        public void call(userInfoBean datas) {
                            tv_userName.setText(datas.getSuccess().getName());
                            tv_email.setText(datas.getSuccess().getEmail());
                            tv_mobilephone.setText(datas.getSuccess().getPhone());
                        }
                    });
        }
    }

    @OnClick({R.id.lin_header_back2,R.id.tv_header_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.lin_header_back2:
                finish();
                break;
            case R.id.tv_header_right:
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://tbox.techroom.com.cn/")//基础URL 建议以 / 结尾
                        .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                        .build();
                interface_retrofit rxjavaService = retrofit.create(interface_retrofit.class);
                if (tv_mobilephone.getText().toString().length() == 11){
                    rxjavaService.changeUserInfo("Bearer " + token,tv_userName.getText().toString(),tv_email.getText().toString(),"",tv_mobilephone.getText().toString())
                            .subscribeOn(Schedulers.io())//IO线程加载数据
                            .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                            .subscribe(new Subscriber<changeUserInfoBean>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(activity_changeInfo.this,"错误信息："+e.getMessage(),Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onNext(changeUserInfoBean data) {
                                    Toast.makeText(activity_changeInfo.this,"修改信息成功",Toast.LENGTH_SHORT).show();
//                                    finish();
                                }
                            });
                }else {
                    Toast.makeText(activity_changeInfo.this,"请输入正确的手机号",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


}
