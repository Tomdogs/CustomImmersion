# CustomImmersion
Android沉浸式表达<br>
最近用到了Android沉浸式状态栏的表达方式，摸索了一段时间，并记录下来。<br>
首先要明白由于Android 4.4（API 19）才加入透明栏，Android 5.0 （API 21）之后才可以直接设置状态栏和导航栏。所以要适配不同版本之间的差异要以
API 19 和 API 21 作为分界线，分别进行不同的设置。<br>
然后，来了解android里几个与沉浸式重点的常量、方法、类。<br>
第一：常量`WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS`和`WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS`
WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS 是系统提供一个最小的`半透明(translucent)`状态栏<br>
WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS 是在状态栏绘制`透明(transparent)`背景并可以绘制颜色<br>
第二：方法`view.setFitsSystemWindows()`和`view.getSystemUiVisibility()`和view.setFitsSystemWindows()<br>
View类提供了setSystemUiVisibility和getSystemUiVisibility方法，这两个方法实现对状态栏的动态显示或隐藏的操作，以及获取状态栏当前可见性.
在setSystemUiVisibility(int visibility)可传入参数：<br>
1.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN：Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住。<br>
2.View.SYSTEM_UI_FLAG_LAYOUT_STABLE:获取一个稳定的视图内容。<br>
3.view.setFitsSystemWindows(booblean):设置系统是否需要考虑System bar（status bar和Navigation bar的统称）占据的区域来显示。如果需要的话就会执行fitSystemWindows(Rect)方法。即设置为true的时候系统会适应System bar 的区域，不内容不被遮住。fitSystemWindows(Rect)(api level 14):用来调整自身的内容来适应System Bar(不让被System Bar遮住)。// 这里其实不止Status Bar和Navigation Bar，只是目前只考虑Status Bar、Navigation Bar、IME。 <br>
onApplyWindowInsets(WindowInsets)(api level 20):同fitSystemWindows(Rect)的作用是一样的，更加方便扩展，对以后增加新的系统控件便于扩展。
第三：
总体来说android沉浸式包括两个方面：①状态栏着色模式②全屏模式（让android内容占据状态栏）<br>

* #状态栏着色

* #全屏模式
