# CustomImmersion
## Android沉浸式表达<br>
[2021 最新梳理-Android 沉浸式状态栏和全面屏遇到刘海屏](https://blog.csdn.net/wanliguodu/article/details/121479399)
### 效果预览
<div  align="center">    
<img src="https://github.com/Tomdogs/CustomImmersion/blob/master/app/src/main/res/mipmap-mdpi/picture1.png" width="300px" />
####
<img src="https://github.com/Tomdogs/CustomImmersion/blob/master/app/src/main/res/mipmap-mdpi/picture2.png" width="300px" />
</div><br>

### 概述
最近用到了Android沉浸式状态栏的表达方式，摸索了一段时间，并记录下来。首先要明白由于Android 4.4（API 19）才加入透明栏，Android 5.0 （API 21）之后才可以直接设置状态栏和导航栏。所以要适配不同版本之间的差异要以API 19 和 API 21 作为分界线，分别进行不同的设置。<br>

### 常用方法变量：

来了解android里几个与沉浸式重点的常量、方法。<br>

* 第一：WindowManager.LayoutParams中提供了几个常量：<br>
`WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS` 是系统提供一个最小的`半透明(translucent)`状态栏<br>
`WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS` 是在system bar（status bar和 navigation bar）绘制`透明(transparent)`背景并可以绘制颜色<br>

* 第二：方法`view.setFitsSystemWindows()`和`view.getSystemUiVisibility()`。<br>
View类提供了`setSystemUiVisibility(int visibility)`和`getSystemUiVisibility()`方法，这两个方法实现对状态栏的动态显示或隐藏的操作，以及获取状态栏当前可见性.<br>
在`setSystemUiVisibility(int visibility)`可传入参数：<br>
  * 1.`View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN`：Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态遮住。<br>
  * 2.`View.SYSTEM_UI_FLAG_LAYOUT_STABLE`:获取一个稳定的视图内容。<br>
* 第三：`view.setFitsSystemWindows()`方法:<br>
  * 1.设置系统是否需要考虑System bar（status bar和Navigation bar的统称）占据的区域来显示。如果需要的话就会执行`fitSystemWindows(Rect)`方法。即设置为true的时候系统会适应System bar 的区域，不内容不被遮住。`fitSystemWindows(Rect)(api level 14)`:用来调整自身的内容来适应System Bar(不让被System Bar遮住)。 这里其实不止Status Bar和Navigation Bar，只是目前只考虑Status Bar、Navigation Bar、IME。 <br>
  * 2.`onApplyWindowInsets(WindowInsets)(api level 20)`:同fitSystemWindows(Rect)的作用是一样的，更加方便扩展，对以后增加新的系统控件便于扩展。<br>
  * 3.使用`android:fitsSystemWindows="true"`，系统会自动的调整显示区域来实现详情的控件不会被遮住。<br>

## Android沉浸式实现
### 总体来说android沉浸式包括两个方面：①状态栏着色模式②全屏模式（让android内容占据状态栏）<br>
### ①状态栏着色模式
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //清除半透明的状态栏
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //系统为 system bar（status bar和 navigation bar）设置透明背景和设置背景颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //为状态栏设置透明背景
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ViewGroup decorView = (ViewGroup) window.getDecorView();
        ViewGroup contentView = (ViewGroup) window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
        View rootView = contentView.getChildAt(0);
        if (rootView != null) {
            //状态栏不被内容遮住
            ViewCompat.setFitsSystemWindows(rootView, true);
        }
### ②全屏模式

    //SDK >= 21(5.0)，5.0开始需要把颜色设置透明，否则状态栏会呈现系统默认的空间
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //需要两个 flag 结合使用，表示让应用的主题内容占用系统的状态栏的空间
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //颜色设置透明
            window.setStatusBarColor(Color.TRANSPARENT);
            int systemUiVisibility = window.getDecorView().getSystemUiVisibility();
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            systemUiVisibility |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            window.getDecorView().setSystemUiVisibility(systemUiVisibility);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ViewGroup decorView = (ViewGroup) window.getDecorView();
        ViewGroup contentView = (ViewGroup) window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
        View rootView = contentView.getChildAt(0);
        int statusBarHeight = getStatusBarHeight(window.getContext());
        if (rootView != null) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) rootView.getLayoutParams();
            //setFitsSystemWindows 可以设置是否系统为rootView预留出空间，但设置为true是会预留出状态栏的空间
            ViewCompat.setFitsSystemWindows(rootView, true);
            lp.topMargin = -statusBarHeight;
            rootView.setLayoutParams(lp);
        }
        setTranslucentView(decorView, alpha);
