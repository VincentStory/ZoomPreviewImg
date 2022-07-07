package com.example.previewpicture.list;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.previewpicture.ImageUrlConfig;
import com.example.previewpicture.R;
import com.example.previewpicture.bean.UserViewInfo;
import com.example.previewpicture.custom.UserFragment;
import com.previewlibrary.GPreviewBuilder;

import java.util.ArrayList;
import java.util.List;

public class ListView2Activity extends AppCompatActivity {
    private ArrayList<UserViewInfo> mThumbViewInfoList = new ArrayList<>();
    ListView listView;
    private MyListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView= (ListView) findViewById(R.id.listView);
        //准备数据
        List<String> urls = ImageUrlConfig.getUrls();
        for (int i = 0; i < urls.size(); i++) {
            mThumbViewInfoList.add(new UserViewInfo(urls.get(i)));
        }
        adapter=new MyListAdapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                computeBoundsBackward( listView.getFirstVisiblePosition());
                GPreviewBuilder.from(ListView2Activity.this)
                        .setData(mThumbViewInfoList)
                        .setCurrentIndex(position)
                        .isDisableDrag(true,0.6f)
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
            View itemView = listView.getChildAt(i-firstCompletelyVisiblePos);
            Rect bounds = new Rect();
            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView.findViewById(R.id.iv);
                thumbView.getGlobalVisibleRect(bounds);
            }
            mThumbViewInfoList.get(i).setBounds(bounds);
        }
    }
    private  class MyListAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mThumbViewInfoList.size();
        }

        @Override
        public Object getItem(int position) {
            return mThumbViewInfoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           View view =getLayoutInflater().inflate(R.layout.item_image,null);
            ImageView iv= (ImageView) view.findViewById(R.id.iv);
            Glide.with(ListView2Activity.this)
                    .load(mThumbViewInfoList.get(position).getUrl())
                    .error(R.mipmap.ic_iamge_zhanwei)
                    .into(iv);
                iv.setTag(R.id.iv,mThumbViewInfoList.get(position));
            return view;
        }
    }

}
