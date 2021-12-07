package com.example.lg.customimmersion

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.view.WindowManager

import androidx.appcompat.app.AppCompatActivity

class StatusBarActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.status_bar_activity)

        val actionBar = supportActionBar
        actionBar?.hide()

//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//        window.statusBarColor = this.resources.getColor(R.color.colorAccent)



//        val decorView = window.decorView
//        val option = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
//        decorView.systemUiVisibility = option

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.statusBarColor = Color.RED
//        }

//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

//        setStatusTransparent19(this)
//        addStatusViewWithColor19(this, Color.RED)

//        transparentStatusBar21(this)
//        transparentStatusBar23(this)


//        EasSystemBar.hideSystemUI1(this)
//        EasSystemBar.hideSystemUI2(this)
//        EasSystemBar.hideSystemUI3(this)

//        supportDisplayCutouts(this)
//        EasSystemBar.setStatusBarShow(this)
//        EasSystemBar.setStatusBarHide(this)

        // 状态栏沉浸式
        findViewById<View>(R.id.bt_immersion_status_show).setOnClickListener {
            EasSystemBar.setContentAdjustToStatusBar(this, true)
//            EasSystemBar.setStatusColor(this, Color.TRANSPARENT, true)
//            dialog()
        }

        // 状态栏变色
        findViewById<View>(R.id.bt_immersion_status_hide).setOnClickListener {
            EasSystemBar.setStatusBarColor(this, Color.RED, true)
//            dialog2()
//            createAndShowDialog(this)
        }


        // 导航栏显示
        findViewById<View>(R.id.bt_navigation_show).setOnClickListener {
            EasSystemBar.setNavigationShow(this)
        }

        // 导航栏隐藏
        findViewById<View>(R.id.bt_navigation_hide).setOnClickListener {
            EasSystemBar.setNavigationBarHide(this)
        }



        // 状态栏显示
        findViewById<View>(R.id.bt_status_show).setOnClickListener {
            EasSystemBar.setSystemBarShow(this)
        }

        // 状态栏隐藏
        findViewById<View>(R.id.bt_status_hide).setOnClickListener {
            EasSystemBar.setStatusBarHide(this)
        }


    }

    private fun setStatusTransparent19(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            val window = activity.window
            val attributes = window.attributes
            val flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            attributes.flags = attributes.flags or flagTranslucentStatus
            window.attributes = attributes

        }
    }

    private fun addStatusViewWithColor19(activity: Activity, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            // 设置 paddingTop
            val rootView = activity.window.decorView.findViewById<View>(android.R.id.content) as ViewGroup
            rootView.setPadding(0, StatusBarUtils.getStatusBarHeight(activity), 0, 0)

            // 增加占位状态栏
            val decorView = activity.window.decorView as ViewGroup
            val statusBarView = View(activity)
            val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, StatusBarUtils.getStatusBarHeight(activity))
            statusBarView.setBackgroundColor(color)
            statusBarView.layoutParams = lp
            decorView.addView(statusBarView)

        }
    }

    private fun transparentStatusBar21(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun transparentStatusBar23(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = Color.TRANSPARENT

            val decorView = window.decorView
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val option = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                decorView.systemUiVisibility = option
            }
        }
    }




    private fun dialog() {
        val layout = inflate(this, R.layout.status_bar_activity, null)
        val dialog = AlertDialog.Builder(this)
            .setView(layout)
            .create()


        val window = dialog.window
        window?.setFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE)
//        widow?.setLayout(resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
//        lp?.width = resources.displayMetrics.widthPixels
//        lp?.height = resources.displayMetrics.heightPixels

        dialog.show()
    }

    private fun dialog2() {
       val dialog = Dialog(this)
        dialog.setContentView(R.layout.status_bar_activity)
        dialog.show()

    }

    // Theme_Black_NoTitleBar_Fullscreen

    fun createAndShowDialog(context: Activity) {
        val dialog = Dialog(context, R.style.MyThemeDialog)
        EasSystemBar.setContentAdjustToStatusBar(context, false)

        dialog.setContentView(R.layout.status_bar_activity)
        val layoutParams = dialog.window!!.attributes
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            resources.displayMetrics.heightPixels + StatusBarUtil.getStatusBarHeight(context)
        )
        dialog.window!!.attributes = layoutParams

        dialog.show()
    }

}