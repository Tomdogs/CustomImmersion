package com.example.lg.customimmersion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ImageImmersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_immersion);

        //沉浸式显示
        SystemBarHelper.immersiveStatusBar(this);
        //状态栏字体颜色为黑色
        SystemBarHelper.setStatusBarDarkMode(this);
    }
}
