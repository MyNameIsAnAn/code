package com.bw.jdxiangmu.wode.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.wode.activity.bean.RegInFo;
import com.bw.jdxiangmu.wode.activity.presenter.MyPresenter;
import com.bw.jdxiangmu.wode.activity.view.MyView;

public class Reg2Activity extends AppCompatActivity implements View.OnClickListener,MyView {

    /**
     * Button
     */
    private Button mButton3;
    /**
     * Name
     */
    private EditText mEditText3;
    /**
     * Name
     */
    private EditText mEditText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg2);
        initView();
    }

    private void initView() {
        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setOnClickListener(this);
        mEditText3 = (EditText) findViewById(R.id.editText3);
        mEditText4 = (EditText) findViewById(R.id.editText4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button3:
                String  s1 = mEditText3.getText().toString();
                 String   s2 = mEditText4.getText().toString();
                MyPresenter myPresenter = new MyPresenter(Reg2Activity.this);
                myPresenter.getPresenter(s1,s2);
                break;
        }
    }

    @Override
    public void OnSuccess(RegInFo jingDong) {
        String msg = jingDong.getMsg();
        Toast.makeText(Reg2Activity.this, msg, Toast.LENGTH_LONG).show();
        startActivity(new Intent(Reg2Activity.this,LoginActivity.class));
    }

    @Override
    public void OnErros(String tjInFo) {
        Toast.makeText(Reg2Activity.this, tjInFo, Toast.LENGTH_LONG).show();
    }
}
