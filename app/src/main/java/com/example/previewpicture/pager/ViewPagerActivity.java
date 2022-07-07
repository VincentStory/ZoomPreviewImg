package com.example.previewpicture.pager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.previewpicture.ImageUrlConfig;
import com.example.previewpicture.R;
import com.example.previewpicture.bean.UserViewInfo;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {
    private ArrayList<UserViewInfo> mThumbViewInfoList = new ArrayList<>();
    ViewPager mViewPager;
    private MyPagerAdaper adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //准备数据
        List<String> urls = ImageUrlConfig.getUrls();
        for (int i = 0; i < 10; i++) {
            mThumbViewInfoList.add(new UserViewInfo(urls.get(i)));
        }
        adapter = new MyPagerAdaper(mThumbViewInfoList, this);
        mViewPager.setAdapter(adapter);

    }

}
