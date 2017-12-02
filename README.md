# SemiCircleProgress
 使用Kotlin编写的半圆形的进度条
 ![效果图](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512195602441&di=5b894f44fe06f26c4dc2cfadd9b99d4b&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F014a52554064690000005b03d35d4e.jpg%40900w_1l_2o_100sh.jpg)

* 导入依赖
```
compile 'com.blackflagbin:semicircleprogressview:0.0.2'
```
* 在布局文件中
```xml
<!--angle 每条短线之间间隔角度-->
<!--progress 进度-->
<!--strokeWidth 每条短线的宽度(粗细)-->

<com.blackflagbin.semicircleprogressview.SemiCircleProgressView
        android:id="@+id/progress"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        app:angle="3"
        app:progress="13"
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


