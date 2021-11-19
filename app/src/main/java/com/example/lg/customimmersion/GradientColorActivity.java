package com.example.lg.customimmersion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class GradientColorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_color);

        SystemBarHelper.tintStatusBar(this, ContextCompat.getColor(this,R.color.colorAccent));
        SystemBarHelper.setStatusBarDarkMode(this);

        //toolbar的相关设置
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackground(ContextCompat.getDrawable(this,R.drawable.shape));
    }
}
