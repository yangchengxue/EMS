package com.example.ycx36.ems.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ycx36.ems.R;
import com.example.ycx36.ems.util.getHwStatusBean;
import com.example.ycx36.ems.util.hw_checkBean;
import com.example.ycx36.ems.util.hw_register;
import com.example.ycx36.ems.util.interface_retrofit;
import com.example.ycx36.ems.util.registerBean;
import com.example.ycx36.ems.view.activity.activity_Register;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class fragment_CarStatus extends Fragment {

    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_carstatus, container, false);

//            Retrofit retrofit2 = new Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())  //Gson解析器
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .baseUrl("https://tbox.techroom.com.cn/")  //数据接口的主地址
//                    .build();
//            interface_retrofit rinterface = retrofit2.create(interface_retrofit.class);
//            rinterface.getHwStatus("voltage","ycx1")  //请求参数
//                .subscribeOn(Schedulers.io())  //观察者切换新线程,subscribe只能调用一次。
//                    .doOnNext(new Action1<getHwStatusBean>() {  //请求结束后调用 doOnNext(),并获data数据
//                        @Override
//                        public void call(getHwStatusBean datas) {
//
//                        }
//                    })
//                    .observeOn(AndroidSchedulers.mainThread())   //被观察者切换到主线程
//                    .subscribe(new Action1<getHwStatusBean>() {    //观察者监听到datas数据,主线程中执行
//                        @Override
//                        public void call(getHwStatusBean datas) {
//                            Log.d("jkhgg21", "  " + datas.getValue());
//                        }
//                    });

//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl("https://tbox.techroom.com.cn/")
//                    .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava 适配器
//                    .build();
//            interface_retrofit rxjavaService = retrofit.create(interface_retrofit.class);
//            rxjavaService.hw_register("ycx2")
//                    .subscribeOn(Schedulers.io())//IO线程加载数据
//                    .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
//                    .subscribe(new Subscriber<hw_register>() {
//                        @Override
//                        public void onCompleted() {
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//
//                        }
//
//                        @Override
//                        public void onNext(hw_register data) {
//                            if (data.isResult()){
//                                Toast.makeText(getActivity(),"硬件注册成功",Toast.LENGTH_SHORT).show();
//                                Log.d("jkhgg","  "+"true1");
//                            }else{
//                                Toast.makeText(getActivity(),"硬件注册失败：编号已注册",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });


        }
        return view;
    }

}
