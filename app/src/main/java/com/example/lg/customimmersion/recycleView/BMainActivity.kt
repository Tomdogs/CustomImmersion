package com.example.lg.customimmersion.recycleView

import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lg.customimmersion.R


class BMainActivity : AppCompatActivity() {

    private var mRecyclerView: RecyclerView?= null
    private val mDatas = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmain)

        for (index in 1..100) {
            mDatas.add("text:$index")
        }
        mRecyclerView = findViewById(R.id.recyclerView)

        val linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView?.layoutManager = linearLayoutManager
        mRecyclerView?.adapter = MyAdapter(mDatas)

        //省略很多RecyclerView的常规操作比如setAdapter和LayoutManager
        mRecyclerView?.setViewCacheExtension(object : RecyclerView.ViewCacheExtension() {
            override fun getViewForPositionAndType(
                recycler: RecyclerView.Recycler,
                position: Int,
                type: Int
            ): View? {
                //从AActivity的缓存中拿View，Demo实例，实际业务可以写的更优雅
                if (AMainActivity.sCustomViewCaches.size != 0) {
                    val view = AMainActivity.sCustomViewCaches.removeFirst()
                    println("custom cache view remove $position $view")
                    Log.d("BMainActivity", "custom cache view remove $position $view")
                    if (position == 0) {
                        println("attention $position $view")
                        Log.d("BMainActivity", "$position $view")
                    }
                    return view
                }
                return null
            }
        })
    }


    class MyAdapter(private val data: ArrayList<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder{
            return MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.custom_cache_view_item, parent, false)
            )
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
           holder.tv.text = data[position]
        }

        override fun getItemCount(): Int {
            return data.size
        }


        class MyViewHolder(view: View) : ViewHolder(view) {
            var tv: TextView = view.findViewById(R.id.heavyText)

        }
    }
}