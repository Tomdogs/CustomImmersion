package com.example.lg.customimmersion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class BarActivity extends AppCompatActivity {

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        actionBar = getSupportActionBar();
    }

    public void showActionBar(View view){
        //设置显示向左的箭头
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("我的actionBar");

        // 设置是否显示应用程序图标
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.show();
    }

    public void hideActionBar(View view){
        actionBar.hide();
    }

    public void toolBar(View view){
        startActivity(new Intent(BarActivity.this,ToolBarActivity.class));

    }

    // 创建上下文菜单时触发该方法
    @Override
    public void onCreateContextMenu(ContextMenu menu, View source,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuInflater inflator = new MenuInflater(this);
        //装填R.menu.context对应的菜单，并添加到menu中
        inflator.inflate(R.menu.context , menu);
        //menu.setHeaderIcon(R.drawable.tools);
        menu.setHeaderTitle("请选择背景色");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.isCheckable()){
            item.setCheckable(true);
        }

        switch (item.getItemId()){
            case android.R.id.home:
                startActivity(new Intent(BarActivity.this,MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
