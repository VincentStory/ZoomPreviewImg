> 本项目是参考github一个开源项目进行了一些优化和库的升级，原项目下载之后还需要跟最新版的studio兼容，
> 项目实现了点击小图放大至全屏预览，退出全屏恢复至原来位置这两个过程的动画过渡。 常见应用场景如微信朋友圈照片九宫格和微信聊天图片,视频,gif预览，某些手机系统相册等viewpager图片查看 缩放 拖拽下拉缩小退出（效果同微信图片浏览）
 
参考项目地址：https://github.com/yangchaojiang/ZoomPreviewPicture-

### 特点
* 1.支持自定义图片加载框架。
* 2.支持重写activity,完成切换切换效果。
* 3.图片查看 缩放 拖拽下拉缩小退出。
* 4.支持自定义activity,Fragment。
* 5.支持视频和自定义视频播放控件。
* 6.支持类似微信朋友圈照片九宫格和微信聊天图片预览。
* 7.指示器类型选择 [圆点模式(贝塞尔圆点指示器)](https://github.com/yanyiqun001/bannerDot)和数字模式。
* 8.增加接口实体类。不在使用数据转化。
* 8.支持GIF显示。

apk下载地址：链接: https://pan.baidu.com/s/1-JxFPsSjoJxps2dBBj9aDQ 提取码: gddy 

### 使用方式：

1.在app的gradle里面依赖这两个库
```
    implementation 'com.ycjiang:ImagePreview:2.3.7'
    implementation 'com.github.bumptech.glide:glide:3.7.0'
```

2.创建一个类TestImageLoader 实现 IZoomMediaLoader 接口

3.在自己的Application里面初始化ZoomMediaLoader
```
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZoomMediaLoader.getInstance().init(new TestImageLoader());
    }
}
```
4.使用
```
 GPreviewBuilder.from(MainActivity.this)
                    .setSingleData(new UserViewInfo(mPath1))
                    .setCurrentIndex(0)
                    .isDisableDrag(true)
                    .setSingleShowType(false)
                    .start();
```
