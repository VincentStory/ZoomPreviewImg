package com.example.previewpicture.custom;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.previewpicture.bean.UserViewInfo;
import com.previewlibrary.view.BasePhotoFragment;

/**
 * author  yangc
 * date 2017/11/22
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated:
 */

public class UserFragment extends BasePhotoFragment {
    /****用户具体数据模型***/
    private UserViewInfo b;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b = (UserViewInfo) getBeanViewInfo();
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("SmoothImageView","onLongClick");
                Toast.makeText(getContext(), "长按事件:" + b.getUser(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(com.previewlibrary.R.layout.fragment_image_photo_layout, container, false);
    }
}
