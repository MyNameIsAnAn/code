package com.bw.jdxiangmu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class DaoJiShiActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private int i = 3;
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dao_ji_shi);
        initView();

        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DaoJiShiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        SharedPreferences gg = getSharedPreferences("gg", MODE_PRIVATE);
        SharedPreferences.Editor edit = gg.edit();
        boolean flag = gg.getBoolean("flag", false);
            if(flag){
                Intent intent = new Intent(DaoJiShiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else{
                edit.putBoolean("flag",true);
                edit.commit();
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTextView2.setText("跳过:("+i+")");
                                if (i == 1) {
                                    Intent intent = new Intent(DaoJiShiActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    timer.cancel();
                                    finish();
                                }
                                i--;
                            }
                        });
                    }
                }, 1, 2000);
            }




    }

    private void initView() {
        mTextView2 = (TextView) findViewById(R.id.textView2);
    }
}
