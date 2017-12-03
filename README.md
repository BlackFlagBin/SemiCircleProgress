# SemiCircleProgress
![版本](https://img.shields.io/twitter/url/https/img.shields.io/badge/Gradle-0.0.3-red.svg.svg?style=social)
* 使用Kotlin编写的半圆形的进度条，效果如下
 
 ![效果图](https://github.com/BlackFlagBin/SemiCircleProgress/blob/master/screenshot.gif?raw=true)

* 导入依赖
```
compile 'com.blackflagbin:semicircleprogressview:0.0.3'
```
* 在布局文件中
```xml
<!--angle 每条短线之间间隔角度-->
<!--progress 进度-->
<!--showProgressText 是否显示进度文字-->
<!--strokeWidth 每条短线的宽度(粗细)-->

<com.blackflagbin.semicircleprogressview.SemiCircleProgressView
        android:id="@+id/progress"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_marginTop="50dp"
        app:angle="3"
        app:progress="13"
        app:showProgressText="true"
        app:strokeWidth="3"/>

```
* 在代码中
```kotlin
//设置进度
progress.setProgress(3f)
//获取进度
val progress = progress.getProgress()
//更新进度
progress.updateProgress(75f)

```
* 如果想要自定义进度文字实现gif演示图中第二个进度条中的类似的文
字效果，可以参考库中的MySemiCircleProgressView
```kotlin
//覆写此方法以实现自定义进度文字，height代表控件的高度
drawProgressText(canvas: Canvas,height:Float)
```

