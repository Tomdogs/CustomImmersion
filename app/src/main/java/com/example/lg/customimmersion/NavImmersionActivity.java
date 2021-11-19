package com.example.lg.customimmersion;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NavImmersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_immersion);

        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setStatusBarDarkMode(this);
    }
}
