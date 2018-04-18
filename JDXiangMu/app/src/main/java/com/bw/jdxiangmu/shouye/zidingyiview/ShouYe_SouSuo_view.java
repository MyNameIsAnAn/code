package com.bw.jdxiangmu.shouye.zidingyiview;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.activity.SeekActivity;
import com.xys.libzxing.zxing.activity.CaptureActivity;


/**
 * Created by lenovo on 2018/4/10.
 */

public class ShouYe_SouSuo_view extends RelativeLayout {

    final int REQUEST_CODE = 8888;

    public ShouYe_SouSuo_view(Context context) {
        this(context, null);
    }

    public ShouYe_SouSuo_view(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShouYe_SouSuo_view(final Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View view = View.inflate(context, R.layout.sousuo2, this);
        ImageView saoyisao = (ImageView) view.findViewById(R.id.saoyisao);
        TextView tv = (TextView) view.findViewById(R.id.sousuo_tv);

        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SeekActivity.class);
                context.startActivity(intent);
            }
        });

        //扫一扫
        saoyisao.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CaptureActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
