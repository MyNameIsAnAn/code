package com.bw.jdxiangmu.wode.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.jdxiangmu.MainActivity;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.utils.SPUtil;
import com.bw.jdxiangmu.wode.activity.bean.LoginInFo;
import com.bw.jdxiangmu.wode.activity.presenter.MyPresenter2;
import com.bw.jdxiangmu.wode.activity.view.MyView2;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, MyView2 {

    /**
     * 请输入手机号
     */
    private EditText mEditText;
    /**
     * 请输入密码
     */
    private EditText mEditText2;
    /**
     * 登录
     */
    private Button mButton;
    /**
     * 注册
     */
    private Button mButton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        initView();

    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(this);
        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                String s1 = mEditText.getText().toString();
                String s2 = mEditText2.getText().toString();
                MyPresenter2 myPresenter2 = new MyPresenter2(this);
                myPresenter2.getPresenter(s1, s2);
                break;
            case R.id.button4:
                startActivity(new Intent(LoginActivity.this, Reg2Activity.class));
                break;
        }
    }

    @Override
    public void OnSuccess(LoginInFo jingDong) {
        LoginInFo.DataBean data = jingDong.getData();
        int uid = data.getUid();
        String nickname = (String) data.getNickname();
        String token = data.getToken();
        Toast.makeText(LoginActivity.this, jingDong.getMsg(), Toast.LENGTH_LONG).show();
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", nickname);
        editor.putString("uid", uid+"");
        editor.putString("token",token);
        editor.commit();
        SPUtil spUtil=new SPUtil(LoginActivity.this,"pddl");
        spUtil.putBoolean("flag",true);
        spUtil.putString("uid",uid+"");


        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void OnErros(String tjInFo) {
        Toast.makeText(LoginActivity.this, tjInFo, Toast.LENGTH_LONG).show();
    }
}
