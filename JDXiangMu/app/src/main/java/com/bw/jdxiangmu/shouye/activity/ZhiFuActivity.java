package com.bw.jdxiangmu.shouye.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.zhifu.AlipayUtil;
import com.bw.jdxiangmu.zhifu.PayResult;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhiFuActivity extends AppCompatActivity {

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0){

                PayResult payResult=new PayResult((String) msg.obj);
                String result = payResult.getResult();
                String resultStatus = payResult.getResultStatus();

                if(TextUtils.equals(resultStatus,"9000")){
                    Toast.makeText(ZhiFuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.equals(resultStatus,"8000")){
                    Toast.makeText(ZhiFuActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ZhiFuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @BindView(R.id.textView)
    TextView mTextView;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.checkbox)
    CheckBox mCheckbox;
    @BindView(R.id.checkbox2)
    CheckBox mCheckbox2;
    @BindView(R.id.button)
    Button mButton;
    boolean flag=true;
    /**
     * 真是开发过程中 这些参数是都不能出现在代码中的 尤其是私钥 放在服务器端
     */
    private final String PARTNER = "2088221871911835"; //Pid
    private final String SELLER = "zhuangshiyigou@163.com";// 商户收款账号
    // 商户私钥，pkcs8格式
    private final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALmhW0c+ZO7sB3utNmkQu5hkpqnw+nAPLCdBoPEw+E+7qcOZBdXxwZsG05yr2BYp5j0MTE0FpKRu+uNTVTAX/MzhcInAColtnWkF1R8rsIdlLdleRoMcM3Lo4XesctdsyxLeummrFLKQfASMqS64kkTUoEFGb3tlYZs7iSFgpM1pAgMBAAECgYAGm6zhK2JycuqNR4xBTzwuX57jO9XeeVvMBfURwPmF9RtFAESJ6jJHL4YG9MMbfuBYWgC5WTMUO3Mo9oV40dHI9dPwANL5aPeKEIUawoQyuCyY/js84fOY3+TbEnXym8G6+Zxm+bGKQn3ZZqlSgR3CHk5f/CceoXvPWDLwl//RtQJBANzDaQTeckTUEtxVyZ9vM26Sv+TKULvqf8OHdrGm7WOWY6/CbChrxMKHxkdrNwZPNIhozNfTaOmnJaPqeMKo8SMCQQDXQmEPHj0h4Qjn3D6n6WiNOvUsNbzVpLP/TgwHFYkRLcz+GPRkbXXdvkSUKxNo7vwZr8vwTIquYA+K3CFTpr4DAkEAu3Ox2NCJdqgc27p8WUSzB1DUYBDqPKYBlqWPw4laSRWJz9Pmwuu/Ru7DDiGbt1/J24ohZaG9k6i57VVK9P8+wQJBAIgGtFrfWvY7xGrwbM+i2aTVqvTDCI9hQzWEVmlrnHA0pyOzFU0ZNrBneeK/zcYzry90PcWeOMy0e13eeVjpN40CQQCMBjVBeTdQ9afgGnBR6glIWrCtqTBAsUIr3gvNZYWdaznr0FmG2pjLwDLUsx0SUCpcrTQxhWu16HDEyQuCm7Ar";
    private String price;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_fu);
        ButterKnife.bind(this);
        price = getIntent().getStringExtra("price");
        title = getIntent().getStringExtra("title");

    }

    @OnClick({R.id.textView, R.id.textView2, R.id.checkbox, R.id.checkbox2, R.id.button})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.textView:
                if(flag){
                    mCheckbox.setChecked(true);
                    flag=false;
                }else if(flag==false){
                    mCheckbox.setChecked(false);
                    flag=true;
                }
                break;
            case R.id.textView2:
                if(flag){
                    mCheckbox2.setChecked(true);
                    flag=false;
                }else if(flag==false){
                    mCheckbox2.setChecked(false);
                    flag=true;
                }
                break;
            case R.id.checkbox:
                if(flag){
                    mCheckbox.setChecked(true);
                    flag=false;
                }else if(flag==false){
                    mCheckbox.setChecked(false);
                    flag=true;
                }
                break;
            case R.id.checkbox2:
                if(flag){
                    mCheckbox2.setChecked(true);
                    flag=false;
                }else if(flag==false){
                    mCheckbox2.setChecked(false);
                    flag=true;
                }
                break;
            case R.id.button:
                //只需要在客户端准备商品的名称/描述/价格 然后调用自己服务器的接口 获取签名后的订单信息即可

                //中间加密+签名+编码这样的过程是在服务器端进行的---->后台需要有Pid商户的id,SELLER商户的收款账号,RSA_PRIVATE商户的私钥

                //需要拿到签名和加密后的订单信息 然后异步调用支付的接口

                //这里需要自己根据实际去传值
                //这是一个还没有完全符合支付宝规范的订单信息
                String orderInfo = AlipayUtil.getOrderInfo(title, "京东商品详情", price,PARTNER,SELLER);

                /**
                 * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！...实际sign签名的字符串是通过与公司服务器签名得到的
                 */
                String sign = AlipayUtil.sign(orderInfo,RSA_PRIVATE);
                try {
                    /**
                     * 仅需对sign 做URL编码
                     */
                    sign = URLEncoder.encode(sign, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                /**
                 * 这个就是符合支付宝规范的订单信息
                 *
                 * 1.签名操作
                 */
                final String signOrderInfo = orderInfo + "&sign=\"" + sign + "\"&" + AlipayUtil.getSignType();

                /**
                 * 由于没有后台 这个demo案例把上面获取订单信息的过程写在本地 ,
                 * 此处需要把Pid商户的id,SELLER商户的收款账号,RSA_PRIVATE商户的私钥 暴露在本地代码中
                 * 而实际不是暴露在本地的
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //开启异步调用支付宝支付接口
                        //1.获取支付宝支付的任务对象
                        PayTask payTask = new PayTask(ZhiFuActivity.this);
                        //2.拿着任务对象 调用支付的接口...signOrderInfo经过签名的订单信息
                        String result = payTask.pay(signOrderInfo, true);


                        //3.把获取的支付结果信息发送到主线程进行操作
                        Message msg = Message.obtain();
                        msg.what = 0;
                        msg.obj = result;
                        handler.sendMessage(msg);
                    }
                }).start();




                break;
        }
    }
}
