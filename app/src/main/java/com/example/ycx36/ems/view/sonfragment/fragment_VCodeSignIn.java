package com.example.ycx36.ems.view.sonfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.example.ycx36.ems.R;
import com.example.ycx36.ems.view.activity.activity_Register;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class fragment_VCodeSignIn extends Fragment {

    @BindView(R.id.at_userNumber) AutoCompleteTextView at_userNumber;
    @BindView(R.id.at_VCode) EditText at_VCode;

    private View view;
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState){
        if (view == null) {
            view = inflater.inflate(R.layout.layout_f_vcode, container, false);
            ButterKnife.bind(this,view);
        }
        return view;
    }


    @OnClick({R.id.bt_sign,R.id.getVcode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_sign:
                login();
                break;
            case R.id.getVcode:
                if(at_userNumber.getText().toString().length() == 11){
                    AVUser.requestLoginSmsCodeInBackground(at_userNumber.getText().toString(), new RequestMobileCodeCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e.getCode() == 213) {
                                Toast.makeText(getActivity(), "手机号码对应的用户不存在", Toast.LENGTH_SHORT).show();
                            }if(e.getCode() == 213) {
                                Toast.makeText(getActivity(), "未验证的手机号码。", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(getActivity(),"手机号不正确",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public void login(){
        if (at_userNumber.getText().toString().equals("") || at_VCode.getText().toString().equals("")){
            Toast.makeText(getActivity(),"请填写信息",Toast.LENGTH_SHORT).show();
        }else {
            AVUser.signUpOrLoginByMobilePhoneInBackground(at_userNumber.getText().toString(), at_VCode.getText().toString(), new LogInCallback<AVUser>() {
                @Override
                public void done(AVUser avUser, AVException e) {

                }
            });
        }
    }
}
