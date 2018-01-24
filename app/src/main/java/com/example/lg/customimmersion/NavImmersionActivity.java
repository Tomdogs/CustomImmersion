package com.example.lg.customimmersion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NavImmersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_immersion);

        SystemBarHelper.immersiveStatusBar(this);
        SystemBarHelper.setStatusBarDarkMode(this);
    }
}
