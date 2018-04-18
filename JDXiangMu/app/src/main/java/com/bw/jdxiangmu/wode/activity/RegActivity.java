package com.bw.jdxiangmu.wode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.fragment.Fragment5;
import com.bw.jdxiangmu.wode.activity.bean.LoginInFo;
import com.bw.jdxiangmu.wode.activity.bean.RegInFo;
import com.bw.jdxiangmu.wode.activity.presenter.MyPresenter;
import com.bw.jdxiangmu.wode.activity.presenter.MyPresenter2;
import com.bw.jdxiangmu.wode.activity.view.MyView;
import com.bw.jdxiangmu.wode.activity.view.MyView2;

public class RegActivity extends AppCompatActivity implements MyView2 {

    /**
     * 请输入手机号
     */
    private EditText mEditText;
    /**
     * 请输入密码
     */
    private EditText mEditText2;
    /**
     * 注册
     */
    private Button mButton;
    private Button mButton2;
    private String s1;
    private String s2;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();

        //注册按钮
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent(RegActivity.this,Reg2Activity.class);
                startActivity(intent);
            }
        });
        //登录按钮
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = mEditText.getText().toString();
                String s2 = mEditText2.getText().toString();
                MyPresenter2 myPresenter2=new MyPresenter2(RegActivity.this);
                myPresenter2.getPresenter(s1,s2);
            }
        });

    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mButton = (Button) findViewById(R.id.button);
        mButton2 = (Button) findViewById(R.id.button2);
    }


    @Override
    public void OnSuccess(LoginInFo jingDong) {
          LoginInFo.DataBean data = jingDong.getData();
             int uid = data.getUid();
          Toast.makeText(RegActivity.this,jingDong.getMsg(),Toast.LENGTH_LONG).show();

    }

    @Override
    public void OnErros(String tjInFo) {
        Toast.makeText(RegActivity.this,tjInFo,Toast.LENGTH_LONG).show();
    }
}
