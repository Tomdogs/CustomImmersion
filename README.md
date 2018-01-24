# CustomImmersion
Android沉浸式表达
最近用到了Android沉浸式状态栏的表达方式，摸索了一段时间，并记录下来。<br>
首先要明白由于Android 4.4（API 19）才加入透明栏，Android 5.0 （API 21）之后才可以直接设置状态栏和导航栏。所以要适配不同版本之间的差异要以
API 19 和 API 21 作为分界线，分别进行不同的设置。<br>
然后，来了解android里几个与沉浸式重点的常量、方法、类。<br>
第一：常量`WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS`和`WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS`
第二：方法setFitsSystemWindows();
第三：
总体来说android沉浸式包括两个方面：①状态栏着色模式②全屏模式（让android内容占据状态栏）<br>

* #状态栏着色

* #全屏模式
