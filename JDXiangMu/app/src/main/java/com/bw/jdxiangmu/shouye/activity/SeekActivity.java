package com.bw.jdxiangmu.shouye.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.activity.adapter.MyAdapterSouSuo;
import com.bw.jdxiangmu.shouye.activity.bean.SouSuoInfo;
import com.bw.jdxiangmu.shouye.activity.sql.SQLite;
import com.fynn.fluidlayout.FluidLayout;
import java.util.ArrayList;
import java.util.List;


public class SeekActivity extends AppCompatActivity implements View.OnClickListener{

    private FluidLayout fluid;
    String[] arrs = {"Mango", "Cuke", "Peach", "Pear", "Orange", "Apple", "Banana"};
    /**
     * 请输入搜索关键字
     */
    private EditText mJ;
    private ImageView mI;
    private RecyclerView mRlv;
    /**
     * 历史搜索
     */
    private Button mLsss;
    private List<String> list;
    /**
     * 取消
     */
    private TextView mQuxiao;
    int page=1;
    private List<SouSuoInfo> list1;
    private SQLiteDatabase db;
    private MyAdapterSouSuo myAdapterSouSuo;
    private Button tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        initView();
        fluid = (FluidLayout) findViewById(R.id.fluid);

        for (int x = 0; x < arrs.length; x++) {
            tv = new Button(this);
            tv.setText(arrs[x]);
            tv.setTextSize(16);

            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(3, 3, 3, 3);
            fluid.addView(tv, params);

        }

        SQLite sqLite=new SQLite(this);
        db = sqLite.getWritableDatabase();
        mQuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = mJ.getText().toString();
                if(TextUtils.isEmpty(s)){
                    Toast.makeText(SeekActivity.this,"请输入您要搜索的商品",Toast.LENGTH_LONG).show();
                }else {
                     db.execSQL("insert into sousuo(ss) values(?)",new Object[]{s});
                    list1 = new ArrayList<>();
                    Cursor cursor = db.rawQuery("select * from sousuo",null);
                    for (int i = 0; i <cursor.getCount() ; i++) {
                        cursor.moveToNext();
                        SouSuoInfo souSuoInfo=new SouSuoInfo();
                        souSuoInfo.setName(cursor.getString(1));
                        list1.add(souSuoInfo);
                    }
                    mRlv.setLayoutManager(new LinearLayoutManager(SeekActivity.this));
                    myAdapterSouSuo = new MyAdapterSouSuo(SeekActivity.this, list1);
                    mRlv.setAdapter(myAdapterSouSuo);

                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("name",s);
                intent.putExtras(bundle);
                intent.setClass(SeekActivity.this, SouSuoShangPinXiangQingActivity.class);
                startActivity(intent);
                }



            }
        });
        list1 = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from sousuo",null);
        for (int i = 0; i <cursor.getCount() ; i++) {
            cursor.moveToNext();
            SouSuoInfo souSuoInfo=new SouSuoInfo();
            souSuoInfo.setName(cursor.getString(1));
            list1.add(souSuoInfo);
        }
        mRlv.setLayoutManager(new LinearLayoutManager(SeekActivity.this));
        myAdapterSouSuo = new MyAdapterSouSuo(SeekActivity.this, list1);
        mRlv.setAdapter(myAdapterSouSuo);
        myAdapterSouSuo.getUrl(new MyAdapterSouSuo.setItem() {
            @Override
            public void OnItem(String item) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("name",item);
                intent.putExtras(bundle);
                intent.setClass(SeekActivity.this, SouSuoShangPinXiangQingActivity.class);
                startActivity(intent);
            }
        });


        mI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        mJ = (EditText) findViewById(R.id.j);
        mI = (ImageView) findViewById(R.id.i);
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mLsss = (Button) findViewById(R.id.lsss);
        mLsss.setOnClickListener(this);
        mQuxiao = (TextView) findViewById(R.id.quxiao);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.lsss:
                db.execSQL("delete from sousuo");
                list1.clear();
                myAdapterSouSuo = new MyAdapterSouSuo(SeekActivity.this, list1);
                mRlv.setAdapter(myAdapterSouSuo);
                break;
        }
    }
}
