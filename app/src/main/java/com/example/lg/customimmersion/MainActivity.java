package com.example.lg.customimmersion;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

}
