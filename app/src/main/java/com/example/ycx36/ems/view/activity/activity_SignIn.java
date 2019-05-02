package com.example.ycx36.ems.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
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
import com.example.ycx36.ems.util.registerBean;
import com.example.ycx36.ems.util.userInfoBean;
import com.example.ycx36.ems.view.sonfragment.fragment_VCodeSignIn;
import com.example.ycx36.ems.view.sonfragment.fragment_passwordSignIn;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


import java.util.ArrayList;
import java.util.List;

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

public class activity_SignIn extends AppCompatActivity {
    @BindView(R.id.viewpager) ViewPager viewpager;
    @BindView(R.id.viewpagertab) SmartTabLayout viewpagertab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sign_in);
        ButterKnife.bind(this);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("密码登录", fragment_passwordSignIn.class)
                .add("验证码登录", fragment_VCodeSignIn.class)
                .create());
        viewpager.setAdapter(adapter);
        viewpagertab.setViewPager(viewpager);

    }

    @OnClick({R.id.tv_header_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_header_back:   //返回
                finish();
                break;
        }
    }




}
