package com.bwei.qizhiwei20180109;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ViewPager vpShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        vpShow = (ViewPager) findViewById(R.id.vp_show);
        vpShow.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                switch (position){
                    case 0:
                        fragment=new Fragment1();
                        break;
                    case 1:
                        fragment=new Fragment2();
                        break;
                    case 2:
                        fragment=new Fragment3();
                        break;
                    case 3:
                        fragment=new Fragment4();
                        break;
                }

                return fragment;
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }
}
