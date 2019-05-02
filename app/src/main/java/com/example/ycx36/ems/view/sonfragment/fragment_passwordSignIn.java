package com.example.ycx36.ems.view.sonfragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.ycx36.ems.R;
import com.example.ycx36.ems.util.interface_retrofit;
import com.example.ycx36.ems.util.loginBean;
import com.example.ycx36.ems.util.registerBean;
import com.example.ycx36.ems.view.activity.activity_Register;
import com.example.ycx36.ems.view.activity.activity_Register_phone;
import com.example.ycx36.ems.view.activity.activity_SignIn;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

public class fragment_passwordSignIn extends Fragment {

    @BindView(R.id.at_userPhone)
    AutoCompleteTextView at_userPhone;
    @BindView(R.id.at_userPassword)
    EditText at_userPassword;
    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.layout_f_passwd, container, false);
            ButterKnife.bind(this,view);
        }
        return view;
    }

    @OnClick({R.id.bt_sign,R.id.register,R.id.changePassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sign:
//                login();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://tbox.techroom.com.cn/")//基础URL 建议以 / 结尾
                        .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                        .build();
                interface_retrofit rxjavaService = retrofit.create(interface_retrofit.class);
                    rxjavaService.login(at_userPhone.getText().toString(),at_userPassword.getText().toString())
                            .subscribeOn(Schedulers.io())//IO线程加载数据
                            .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                            .subscribe(new Subscriber<loginBean>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(getActivity(),"错误信息："+e.getMessage(),Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onNext(loginBean data) {
                                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("TokenInfo",MODE_PRIVATE).edit();
                                    editor.putString("token",data.getSuccess().getToken());
                                    editor.apply();
                                    Toast.makeText(getActivity(),"欢迎您："+data.getSuccess().getName(),Toast.LENGTH_SHORT).show();
                                    getActivity().finish();
                                }
                            });
                break;
            case R.id.register:
                Intent intent = new Intent(getActivity(),activity_Register.class);
                startActivity(intent);
                break;
            case R.id.changePassword:
                break;
        }
    }

    public void login() {
        if ("".equals(at_userPhone.getText().toString()) && "".equals(at_userPassword.getText().toString())){
            Toast.makeText(getActivity(), "请输入登录信息", Toast.LENGTH_SHORT).show();
        }else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://tbox.techroom.com.cn/")//基础URL 建议以 / 结尾
                    .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
                    .build();
            interface_retrofit rxjavaService = retrofit.create(interface_retrofit.class);
            if (at_userPhone.getText().toString().length() == 11){
                rxjavaService.login(at_userPhone.getText().toString(),at_userPassword.getText().toString())
                        .subscribeOn(Schedulers.io())//IO线程加载数据
                        .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
                        .subscribe(new Subscriber<loginBean>() {
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getActivity(),"错误信息："+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onNext(loginBean data) {
                                Toast.makeText(getActivity(),"欢迎您，"+data.getSuccess().getName(),Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }
                        });
            }else {
                Toast.makeText(getActivity(),"请输入正确的手机号",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
