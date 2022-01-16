package com.example.lg.customimmersion;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lg.customimmersion.okhttp.OkhttpActivity;
import com.example.lg.customimmersion.recycleView.AMainActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void button1(View view){
        startActivity(new Intent(MainActivity.this,BarActivity.class));
    }


    public void button2(View view){
        startActivity(new Intent(MainActivity.this,ImageImmersionActivity.class));
    }


    public void button3(View view){
        startActivity(new Intent(MainActivity.this,NavImmersionActivity.class));
    }

    public void button4(View view){
        startActivity(new Intent(MainActivity.this,GradientColorActivity.class));
    }

    /**
     * 调暗系统栏
     *
     * @param view
     */
    public void button5(View view) {
        startActivity(new Intent(MainActivity.this, DarkenSystemUIActivity.class));
    }

    public void button6(View view) {
        startActivity(new Intent(MainActivity.this, StatusBarActivity.class));
    }

    public void button7(View view) {
        startActivity(new Intent(MainActivity.this, AMainActivity.class));
    }

    public void button8(View view) {
        startActivity(new Intent(MainActivity.this, OkhttpActivity.class));
    }
}
