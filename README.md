# java_opencv

#### 项目介绍
OpenCV是一个基于BSD许可（开源）发行的跨平台计算机视觉库，它提供了一系列图像处理和计算机视觉方面很多通用算法。是研究图像处理技术的一个很不错的工具。最初开始接触是2016年因为公司项目需要，但是当时网上可供参考的demo实在太少了，而且基本上都是基于C、C++实现的。也就是从2017年开始，关于java+opencv的资料才渐渐多起来。处于这种情况，就想搭建一个有助于我们学习和了解opencv的一个平台。因此就有了这个系统。
从安装开始，和大家一起学习记录OpenCV的相关知识，直至最终一个简单但完整DEMO的实现（答题卡识别）。

#### 软件架构
SpringMVC+AdminLTE 2+maven。考虑到之前demo测试，要么都是生成图片查看效果，要么用swing绘制，遇到参数变化的时候，不便于调试，于是就做成了大家熟悉的web模式。后台是基于SpringMVC，也没有数据库交互，就是个页面操作效果实时查看的功能，现在很简单。前端使用的是AdminLTE 2，一个基于 bootstrap 的轻量级后台模板。


#### 安装教程

1. 下载项目maven更新
2. 执行readme中需要的本地maven依赖jar
3. 如果需要使用tesseract方式识别页码，注意tessdata放到tomcat的bin目录下
4. 参照项目中JAVA集成OpenCV，配置linux或windows环境
5. 数据库配置目前用不到，可以直接去掉spring-context.xml中关于数据源的配置，也可以随便指定个连接数据库地址
6. 有的小伙伴@武 在tomcat启动项目调用时会出现UnstatisfiedLinkError:no opencv_java2413 in java.library.path 异常信息，可以通过输出System.out.println(System.getProperty("java.library.path"));打印一下本地的library地址。将dll文件放到输出的地址中，如jdk的bin目录下;tomcat的bin目录等，重启tomcat就可以解决该问题。

#### 主要内容
1. 图像二值化
2. 图像自适用二值化
3. 高斯模糊
4. 图片缩放
5. 腐蚀膨胀
6. 进阶形态学变换
7. 边缘检测
8. 检测直线
9. 检测圆
10. 检测颜色
11. 轮廓识别
12. 模板查找
13. 绘制灰度直方图
14. 答题卡识别demo
15. 图像矫正（透视变换）

#### 系统效果

![输入图片说明](https://gitee.com/uploads/images/2018/0507/182215_42dcefbf_102358.png "屏幕截图.png")
![输入图片说明](https://gitee.com/uploads/images/2018/0507/182320_394b0221_102358.png "屏幕截图.png")
![输入图片说明](https://gitee.com/uploads/images/2018/0507/182258_b64a9246_102358.png "屏幕截图.png")

#### 支持

有疑问想进行咨询可以通过如下方式，能帮到的尽量帮大家。
邮箱 ws01986@163.com
QQ 434923959
QQ群：709883689
另外正在陆续实现一个拍照识别答题卡的小程序，可以微信小程序搜索“扫扫阅卷”
####
![输入图片说明](https://images.gitee.com/uploads/images/2018/1213/174404_f48cb9af_102358.png "1.png")
![输入图片说明](https://images.gitee.com/uploads/images/2018/1213/174418_43a27250_102358.png "2.png")