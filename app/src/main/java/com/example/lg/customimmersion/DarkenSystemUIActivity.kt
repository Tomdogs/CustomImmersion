package com.example.lg.customimmersion

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class DarkenSystemUIActivity : AppCompatActivity() {

    private lateinit var systemBarManager : SystemBarTintManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_darken_system_uiactivity)

//        val contentView = getContentView()
//        if (contentView != null) {
//            contentView.fitsSystemWindows = true
//        }
//        setTranslucentStatus(true)
//
//        systemBarManager = SystemBarTintManager(this)
//        systemBarManager.isStatusBarTintEnabled = true
//        systemBarManager.setTintColor(resources.getColor(R.color.transparent))

        // 导航栏显示
        findViewById<View>(R.id.bt_immersion_status_show).setOnClickListener {
            showSystemUI()
//            systemBarManager.setTintColor(resources.getColor(R.color.colorAccent))
//            setStatusBarColor(this, resources.getColor(R.color.colorAccent))
        }

        // 导航栏隐藏
        findViewById<View>(R.id.bt_immersion_status_hide).setOnClickListener {
//            hideSystemUI()
//            StatusBarUtils(this).fullScreen(this)
//            setStatusBarColor(this, resources.getColor(R.color.transparent))
//            systemBarManager.setTintColor(resources.getColor(R.color.transparent))
        }
    }

    fun setTranslucentStatus(on: Boolean) {
        val win = window
        val winParams = win.attributes
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    protected fun getContentView(): View? {
        val view = window.decorView as ViewGroup
        val content = view.findViewById<View>(android.R.id.content) as FrameLayout
        return content.getChildAt(0)
    }

    private fun hideSystemUI() {
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
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }


    /**
     * 设置状态栏的颜色
     *
     * @param activity
     * @param color
     */
    private fun setStatusBarColor(activity: Activity, color: Int) {
        // 设置 paddingTop
        activity.window.decorView.findViewById<View>(android.R.id.content)
            .setPadding(0, StatusBarUtils.getStatusBarHeight(activity), 0, 0)

        // 直接设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = color
        } else {
            // 增加占位状态栏
            val decorView = activity.window.decorView as? ViewGroup
            val statusBarView = View(activity)
            val lp = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                StatusBarUtils.getStatusBarHeight(activity))

            statusBarView.setBackgroundColor(color)
            decorView?.addView(statusBarView, lp)

        }

    }
}