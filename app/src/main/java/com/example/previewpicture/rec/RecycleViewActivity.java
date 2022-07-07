package com.example.previewpicture.rec;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.previewpicture.ImageUrlConfig;
import com.example.previewpicture.R;
import com.example.previewpicture.bean.UserViewInfo;
import com.previewlibrary.GPreviewBuilder;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends Activity {
    private ArrayList<UserViewInfo> mThumbViewInfoList = new ArrayList<>();
    private GridLayoutManager mGridLayoutManager;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        init();
    }
    //初始化数据和控件
    private void init(){
        //准备数据
        List<String> urls = ImageUrlConfig.getUrls();
        for (int i = 0; i < 30; i++) {
            mThumbViewInfoList.add(new UserViewInfo(urls.get(i)));
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mGridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        MyBaseQuickAdapter adapter=new MyBaseQuickAdapter(this);
        adapter.addData(mThumbViewInfoList);
        mRecyclerView.setAdapter(adapter);
       adapter.setOnItemClickListener(new OnItemClickListener() {
           @Override
           public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
               computeBoundsBackward(mGridLayoutManager.findFirstVisibleItemPosition());
               GPreviewBuilder.from(RecycleViewActivity.this)
                       .setData(mThumbViewInfoList)
                       .setCurrentIndex(position)
                       .setSingleFling(true)
                       .setType(GPreviewBuilder.IndicatorType.Number)
                       .start();
           }
       });


    }
    /**
     * 查找信息
     * 从第一个完整可见item逆序遍历，如果初始位置为0，则不执行方法内循环
     */
    private void computeBoundsBackward(int firstCompletelyVisiblePos) {
        for (int i = firstCompletelyVisiblePos;i < mThumbViewInfoList.size(); i++) {
            View itemView = mGridLayoutManager.findViewByPosition(i);
            Rect bounds = new Rect();
            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView.findViewById(R.id.iv);
                thumbView.getGlobalVisibleRect(bounds);
            }
            mThumbViewInfoList.get(i).setBounds(bounds);
        }
    }
}
