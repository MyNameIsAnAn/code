package com.bw.jdxiangmu.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.jdxiangmu.R;
import com.bw.jdxiangmu.shouye.activity.DingDanActivity;
import com.bw.jdxiangmu.wode.activity.LoginActivity;
import com.bw.jdxiangmu.wode.fragment.F1;
import com.bw.jdxiangmu.wode.fragment.F2;
import com.bw.jdxiangmu.wode.fragment.F3;
import com.bw.jdxiangmu.wode.fragment.F4;
import com.bw.jdxiangmu.wode.fragment.F5;
import com.bw.jdxiangmu.wode.fragment.F6;
import com.bw.jdxiangmu.wode.utils.UploadUtil;
import com.bw.jdxiangmu.wode.view.UpDateView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * Created by lenovo on 2018/1/31.
 */

public class Fragment5 extends Fragment implements UpDateView ,UploadUtil.OnUploadProcessListener{
    private ImageView mImageView;
    private static final int PHOTO_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private static final int PHOTO_CLIP = 3;
    private static final int UPLOAD_INIT_PROCESS = 4;//上传初始化
    protected static final int UPLOAD_FILE_DONE = 2;//上传中
    private static final int UPLOAD_IN_PROCESS = 5;//上传文件响应

    private ProgressDialog pd;

    private File filepath;

    @BindView(R.id.dingdan)
    ImageView mDingdan;
    private View view;
    private RecyclerView mRlv;
    String string = "https://www.zhaoapi.cn/ad/getAd";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    F1 f1;
    F2 f2;
    F3 f3;
    F4 f4;
    F5 f5;
    F6 f6;
    List<Fragment> fragmentList;
    String[] strings = {"精选推荐", "酷跑一族", "钢铁洪流", "要温度", "中国风", "调味生活"};
    private TextView mTextView3;
    private TextView mTextView4;
    private String s;
    private String uid;
    private String token;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment5, container, false);
        initView(view);
        f1 = new F1();
        f2 = new F2();
        f3 = new F3();
        f4 = new F4();
        f5 = new F5();
        f6 = new F6();

        fragmentList = new ArrayList<>();
        fragmentList.add(f1);
        fragmentList.add(f2);
        fragmentList.add(f3);
        fragmentList.add(f4);
        fragmentList.add(f5);
        fragmentList.add(f6);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return strings[position];
            }
        });

        /*mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), RegActivity.class);
                startActivity(intent);
            }
        });*/
        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        final String str = sharedPreferences.getString("name", null);
        token = sharedPreferences.getString("token", null);
        uid = sharedPreferences.getString("uid", null);
        if (str != null) {
            mTextView3.setText(str);
        }
        unbinder = ButterKnife.bind(this, view);

        mDingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("uid",uid+"");
                intent.putExtras(bundle);
                intent.setClass(getActivity(), DingDanActivity.class);
                startActivity(intent);
            }
        });

        mImageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog builder = new AlertDialog.Builder(getActivity())
                    .setTitle("选择头像")
                    .setPositiveButton("相机", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getCamera();

                        }
                    })
                    .setNegativeButton("相册", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getPhoto();

                        }
                    })
                    .show();
        }
    });

        return view;
    }

    private void initView(View view) {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        tabLayout = (TabLayout) view.findViewById(R.id.tab);
        viewPager = (ViewPager) view.findViewById(R.id.vippp);
        mImageView = (ImageView) view.findViewById(R.id.img);
        mTextView3 = (TextView) view.findViewById(R.id.textView3);
    }

    @Override
    public void OnSuccess(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void OnError(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
    }

    private void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, PHOTO_REQUEST);
    }

    private void getCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 下面这句指定调用相机拍照后的照片存储的路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                Environment.getExternalStorageDirectory(), "hand.jpg")));
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private void photoClip(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_CLIP);
    }


    private void toUploadFile() {
        pd = ProgressDialog.show(getActivity(), "", "正在上传文件...");
        pd.show();
        String fileKey = "avatarFile";
        UploadUtil uploadUtil = UploadUtil.getInstance();
        uploadUtil.setOnUploadProcessListener(this); //设置监听器监听上传状态

        Map<String, String> params = new HashMap<String, String>();//上传map对象
        params.put("userId", "");
        uploadUtil.uploadFile(filepath, fileKey, "上传头像的地址", params);
        Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onUploadDone(int responseCode, String message) {

        pd.dismiss();
        Message msg = Message.obtain();
        msg.what = UPLOAD_FILE_DONE;
        msg.arg1 = responseCode;
        msg.obj = message;
    }

    @Override
    public void onUploadProcess(int uploadSize) {

        //上传中
        Message msg = Message.obtain();
        msg.what = UPLOAD_IN_PROCESS;
        msg.arg1 = uploadSize;
    }

    @Override
    public void initUpload(int fileSize) {

        //准备上传
        Message msg = Message.obtain();
        msg.what = UPLOAD_INIT_PROCESS;
        msg.arg1 = fileSize;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CAMERA_REQUEST:
                switch (resultCode) {
                    case -1://-1表示拍照成功
                        File file = new File(Environment.getExternalStorageDirectory()
                                + "/hand.jpg");//保存图片
                        if (file.exists()) {
                            //对相机拍照照片进行裁剪
                            photoClip(Uri.fromFile(file));
                        }
                }
                break;

            case PHOTO_REQUEST://从相册取
                if (data != null) {
                    Uri uri = data.getData();
                    //对相册取出照片进行裁剪
                    photoClip(uri);

                }
                break;
            case PHOTO_CLIP:
                //完成
                if (data != null) {

                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        try {
                            //获得图片路径
                            filepath = UploadUtil.saveFile(photo, Environment.getExternalStorageDirectory().toString(), "hand.jpg");
                            //上传照片
                            toUploadFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //上传完成将照片写入imageview与用户进行交互
                        mImageView.setImageBitmap(photo);
                    }
                }
                break;
        }
    }




}
