package com.example.ycx36.ems.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;
import com.example.ycx36.ems.R;
import com.example.ycx36.ems.util.interface_retrofit;
import com.example.ycx36.ems.util.userInfoBean;
import com.example.ycx36.ems.view.activity.MainActivity;
import com.example.ycx36.ems.view.activity.activity_SignIn;
import com.example.ycx36.ems.view.activity.activity_changeInfo;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

public class fragment_Mine extends Fragment {

    @BindView(R.id.userDrawee) SimpleDraweeView userDrawee;
    @BindView(R.id.currentUsername) TextView currentUsername;
    @BindView(R.id.bt_signIn_Out) Button bt_signIn_Out;
    private AVUser currentUser;

    String token;

    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mine, container, false);
            ButterKnife.bind(this,view);

        }
        return view;
    }

    @OnClick({R.id.RL_1,R.id.RL_2,R.id.RL_3,R.id.RL_4,R.id.RL_5,R.id.RL_6,R.id.userDrawee,R.id.bt_signIn_Out})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.RL_1:
                if (!token.equals("")){
                    startActivity(new Intent(getActivity(),activity_changeInfo.class));
                }else {
                    startActivity(new Intent(getActivity(),activity_SignIn.class));
                }
                break;
            case R.id.RL_2:
                break;
            case R.id.RL_3:
                break;
            case R.id.RL_4:
                break;
            case R.id.RL_5:
                break;
            case R.id.RL_6:
                break;
            case R.id.userDrawee:
                Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
                intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/png");
                startActivityForResult(intentToPickPic, 1);
                break;
            case R.id.bt_signIn_Out:
                if (bt_signIn_Out.getText() == "退出登录"){
                    SignOut();
                }else{
                    startActivity(new Intent(getActivity(),activity_SignIn.class));
                }
                break;
        }
    }

    /**退出登录*/
    public void SignOut(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());//创建一个提示对话框的构造者
        builder.setTitle("提示");  //设置对话框的标题
        builder.setMessage("是否登出当前用户？");  //设置提示信息
        builder.setIcon(R.mipmap.ic_launcher);  //设置对话框的图标

        //设置正面的按钮，输入new DialogInterface.OnClickListener()对自动弹出方法。
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //退出登录的逻辑
            }
        });
        //设置反面的按钮，输入new DialogInterface.OnClickListener()对自动弹出方法。
        builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //设置中立的按钮，输入new DialogInterface.OnClickListener()对自动弹出方法。
        builder.setNeutralButton("隐藏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    private Uri imageUri;
    //当点击某涨照片完成时会回调到onActivityResult 在这里处理照片
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MainActivity.RESULT_OK) {
            switch (requestCode) {
                case 1: {
                    // 获取图片
                    try {
                        //该uri是上一个Activity返回的
                        imageUri = data.getData();
                        if (imageUri != null) {
                            AVUser.getCurrentUser().saveInBackground(new SaveCallback() {
                                @Override
                                public void done(AVException e) {
                                    //图片上传至云端
                                    final AVFile file;
                                    try {
                                        file = AVFile.withAbsoluteLocalPath("LeanCloud.png", getRealPathFromUri(getContext(), imageUri));
                                        file.saveInBackground(new SaveCallback() {
                                            @Override
                                            public void done(AVException e) {
                                                currentUser.put("userPictureUri", file.getUrl());  //将上传至云端的地址 存入_User表
                                                currentUser.saveInBackground();
                                            }
                                        });
                                    } catch (FileNotFoundException e1) {
                                        e1.printStackTrace();
                                    }

                                }
                            });
                            userDrawee.setImageURI(imageUri);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 将相对路径转化为绝对路径
     */
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void onResume() {
        super.onResume();
        SharedPreferences pref = getActivity().getSharedPreferences("TokenInfo",MODE_PRIVATE);
        token = pref.getString("token","");
        Retrofit retrofit2 = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())  //Gson解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://tbox.techroom.com.cn/")  //数据接口的主地址
                .build();
        interface_retrofit rinterface = retrofit2.create(interface_retrofit.class);
        if (!token.equals("")){
            rinterface.getUserInfo("Bearer "+token)
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
                            currentUsername.setText(datas.getSuccess().getName());
                        }
                    });

            userDrawee.setImageURI("");
            bt_signIn_Out.setText("退出登录");
        }
    }

}
