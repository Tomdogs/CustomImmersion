package com.example.lg.customimmersion

import android.app.Activity
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.util.Log
import android.view.*
import android.view.WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES

/**
 *
 *
 * @author: luoguodu
 * @version : v3.5.0
 * @since: 2021/12/3
 *
 */
object EasSystemBar {

    /**
     * 使 contentView 中的内容占据到状态栏的沉浸式
     * 和 [setStatusBarColor] 是互斥的
     *
     *
     * @param activity
     */
    fun setContentAdjustToStatusBar(activity: Activity, isLight: Boolean) {

        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = Color.TRANSPARENT

            // 部分手机上设置此标识无效
//            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            if (isLight) {
                setStatusBarLight(activity)
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

    }


    /**
     * 设置状态栏颜色，contentView 的内容在状态栏的下方的沉浸式
     * 和 [setContentAdjustToStatusBar] 是互斥的
     *
     * @param activity
     * @param color
     * @param isLight
     */
    fun setStatusBarColor(activity: Activity, color: Int, isLight: Boolean) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = color

            if (isLight) {
                setStatusBarLight(activity)
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            // 设置 paddingTop
            val rootView = activity.window.decorView.findViewById<View>(android.R.id.content) as ViewGroup
            rootView.setPadding(0, getStatusBarHeight(activity), 0, 0)

            // 增加占位状态栏
            val decorView = activity.window.decorView as ViewGroup
            val statusBarView = View(activity)
            val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
            statusBarView.setBackgroundColor(color)
            statusBarView.layoutParams = lp
            decorView.addView(statusBarView)

        }
    }


    /**
     * 状态栏的图标和文字颜色为暗色
     */
    fun setStatusBarLight(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val option = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.decorView.systemUiVisibility = option
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param activity
     * @return
     */
    fun getStatusBarHeight(activity: Activity): Int {
        var result = 0
        //获取状态栏高度的资源id
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = activity.resources.getDimensionPixelSize(resourceId)
        }
        Log.d("getStatusBarHeight", result.toString() + "")
        return result
    }

    /**
     * 状态栏显示
     * 只要清除原来状态栏的隐藏标志就可以显示出状态栏
     *
     * @param activity
     */
    fun setSystemBarShow(activity: Activity) {
        val window = activity.window
        window.decorView.systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )

    }

    /**
     * 导航栏显示
     * 只要清除原来导航栏的隐藏标志就可以显示出导航栏
     *
     * @param activity
     */
    fun setNavigationShow(activity: Activity) {
        val window = activity.window
        window.decorView.systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)

    }

    /**
     * 状态栏隐藏
     *
     * @param activity
     */
    fun setStatusBarHide(activity: Activity) {
        // 允许窗口延伸到屏幕短边上的刘海区域
//        supportDisplayCutouts(activity)
        val window = activity.window
        window.decorView.systemUiVisibility = (
                // 预留控制内容到状态栏的距离
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                // 隐藏状态栏
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                // 将 contentView的内容延伸到状态栏。
                // 设置此属性时，要考虑是否使用 fitSystemWindows 来填充状态栏的距离；
                // 还要考虑刘海屏的情况，使用 layoutInDisplayCutoutMode 设置
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                )

    }

    /**
     * 导航栏隐藏
     *
     * @param activity
     */
    fun setNavigationBarHide(activity: Activity) {
        val window = activity.window

        window.decorView.systemUiVisibility = (
                // 预留控制内容到导航栏的距离
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                // 隐藏导航栏
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                // 将 contentView的内容延伸到导航栏。
                // 设置此属性时，要考虑是否使用 fitSystemWindows 来填充导航栏的距离；
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                )
    }

    /**
     * 隐藏系统栏（状态栏和导航栏）
     *
     * @param activity
     */
    fun hideSystemUI(activity: Activity) {
        val window = activity.window
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                )
    }

    /**
     *  向后倾斜
     *
     * @param activity
     */
    fun hideSystemUI1(activity: Activity) {
        val window = activity.window
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    /**
     * 沉浸模式
     *
     * @param activity
     */
    fun hideSystemUI2(activity: Activity) {
        val window = activity.window
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    /**
     * 粘性沉浸模式
     *
     * @param activity
     */
    fun hideSystemUI3(activity: Activity) {
        val window = activity.window
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }


    /**
     * 显示系统栏（状态栏和导航栏）
     *  hows the system bars by removing all the flags
     *  except for the ones that make the content appear under the system bars.
     *
     * @param activity
     */
    fun showSystemUI(activity: Activity) {
        val window = activity.window
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }


    /**
     * 支持刘海屏，
     * 始终允许窗口延伸到屏幕短边上的刘海区域
     *
     * @param activity
     */
    fun supportDisplayCutouts(activity: Activity) {
        val window = activity.window
        val lp = window.attributes
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // 仅当缺口区域完全包含在状态栏之中时，才允许窗口延伸到刘海区域显示
//            lp.layoutInDisplayCutoutMode =
//                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
            // 永远不允许窗口延伸到刘海区域
//            lp.layoutInDisplayCutoutMode =
//                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER
            // 始终允许窗口延伸到屏幕短边上的刘海区域
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }

    }

    /**
     * android 30 以及以后的方式
     *
     * @param activity
     */
    private fun hideSystemBars30s(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = activity.window.decorView.windowInsetsController

            // 设置状态栏反色
            controller?.setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS)
            // 取消状态栏反色
            controller?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
            // 设置导航栏反色
            controller?.setSystemBarsAppearance(APPEARANCE_LIGHT_NAVIGATION_BARS, APPEARANCE_LIGHT_NAVIGATION_BARS)
            // 取消导航栏反色
            controller?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_NAVIGATION_BARS)
            // 同时设置状态栏和导航栏反色
            controller?.setSystemBarsAppearance((APPEARANCE_LIGHT_STATUS_BARS or APPEARANCE_LIGHT_NAVIGATION_BARS),
                (APPEARANCE_LIGHT_STATUS_BARS or APPEARANCE_LIGHT_NAVIGATION_BARS))
            // 同时取消状态栏和导航栏反色
            controller?.setSystemBarsAppearance(0, (APPEARANCE_LIGHT_STATUS_BARS or APPEARANCE_LIGHT_NAVIGATION_BARS))
            // 隐藏状态栏
            controller?.hide(WindowInsets.Type.statusBars())
            // 显示状态栏
            controller?.show(WindowInsets.Type.statusBars())
            // 隐藏导航栏
            controller?.hide(WindowInsets.Type.navigationBars())
            // 显示导航栏
            controller?.show(WindowInsets.Type.navigationBars())
            // 同时隐藏状态栏和导航栏
            controller?.hide(WindowInsets.Type.systemBars())
            // 同时显示状态栏和导航栏
            controller?.show(WindowInsets.Type.systemBars())

            // 向后模式
            val behavior1 = WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_TOUCH
            // 沉浸模式
            val behavior2 = WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE
            // 粘性沉浸模式
            val behavior3 = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            controller?.systemBarsBehavior = behavior1
        }

    }
}