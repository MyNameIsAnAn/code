package com.bw.jdxiangmu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bw.jdxiangmu.fragment.Fragment1;
import com.bw.jdxiangmu.fragment.Fragment2;
import com.bw.jdxiangmu.fragment.Fragment3;
import com.bw.jdxiangmu.fragment.Fragment4;
import com.bw.jdxiangmu.fragment.Fragment5;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
    private List<Fragment> fragmentList;
    private ViewPager viewPager;
    private FrameLayout mFrame;


    private RadioGroup mRadio;
    private ViewPager mVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment5);
        mVip.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });

        mVip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:
                        mRadio.check(R.id.radiobutton1);
                        break;
                    case 1:
                        mRadio.check(R.id.radiobutton2);
                        break;
                    case 2:
                        mRadio.check(R.id.radiobutton3);
                        break;
                    case 3:
                        mRadio.check(R.id.radiobutton4);
                        break;
                    case 4:
                        mRadio.check(R.id.radiobutton5);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radiobutton1:
                        mVip.setCurrentItem(0);
                        break;
                    case R.id.radiobutton2:
                        mVip.setCurrentItem(1);
                        break;
                    case R.id.radiobutton3:
                        mVip.setCurrentItem(2);
                        break;
                    case R.id.radiobutton4:
                        mVip.setCurrentItem(3);
                        break;
                    case R.id.radiobutton5:
                        mVip.setCurrentItem(4);
                        break;

                }
            }
        });
    }

    private void initView() {
        mRadio = (RadioGroup) findViewById(R.id.radio);
        mVip = (ViewPager) findViewById(R.id.vip);
    }


}
