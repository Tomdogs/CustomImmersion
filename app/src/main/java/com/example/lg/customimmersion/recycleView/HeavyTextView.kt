package com.example.lg.customimmersion.recycleView

import android.content.Context
import android.util.AttributeSet
import android.util.Log

class HeavyTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyleAttr) {
    init {
        println("heavy view init")
        Log.d("recyclerView优化", "heavy view init")
        Thread.sleep(100L)
    }
}