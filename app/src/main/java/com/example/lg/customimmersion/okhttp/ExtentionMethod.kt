package com.example.lg.customimmersion.okhttp

import android.util.Log

/**
 *
 *
 * @author: luoguodu
 * @version : v3.5.0
 * @since: 2022/1/13
 *
 */
class ExtentionMethod {

//    fun String.ad2d(string: String) {
//        Log.d("tag", "string 扩展add")
//    }


    //    "dfd".add2()
    val str = String().add(9)




}


fun String.add(num: Int): String {
    Log.d("tag", "string 扩展add")
    return this.plus(num)
}