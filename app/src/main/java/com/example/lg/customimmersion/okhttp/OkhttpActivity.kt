package com.example.lg.customimmersion.okhttp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lg.customimmersion.R
import okhttp3.*

import java.io.IOException
import java.lang.Exception


class OkhttpActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "LoggingInterceptor"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)


        Log.d(TAG, "onCreate")


//        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
//            .addInterceptor(LoggingInterceptor())
//            .addInterceptor(HeaderInterceptor())
//            .build()
//
//        val request: Request = Request.Builder()
//            .url("https://www.publicobject.com/helloworld.txt")
//            .header("User-Agent", "OkHttp Example")
//            .build()
//
//        okHttpClient.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.d(TAG, "onFailure: " + e.message)
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val body = response.body
//                if (body != null) {
//                    Log.d(TAG, "onResponse: " + response.body!!.string())
//                    body.close()
//                }
//            }
//
//        })
    }

    class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request: Request = chain.request()
            val startTime = System.nanoTime()
            Log.d(
                TAG, java.lang.String.format(
                    "Sending request %s on %s%n%s",
                    request.url, chain.connection(), request.headers
                )
            )
            val response: Response = chain.proceed(request)
            val endTime = System.nanoTime()
            Log.d(
                TAG, String.format(
                    "Received response for %s in %.1fms%n%s",
                    response.request.url, (endTime - startTime) / 1e6, response.headers
                )
            )
            return response
        }
    }


    class HeaderInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
//            val referer: String = HttpUrl.Builder()
//                .scheme("https")
//                .build()
//                .toString()
//            val requestBuilder: Request.Builder = chain.request().newBuilder()
//            requestBuilder.header("Referer", referer)
//            try {
//                requestBuilder.header("User-Agent", "lgd--666")
//            } catch (e: Exception) {
//                requestBuilder.header("User-Agent", "lgd--888")
//            }
//            val request: Request = requestBuilder.build()

            val request = chain.request()

            Log.d(TAG, "HeaderInterceptor,----request:$request")
            return chain.proceed(request)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent")
    }

    fun click(view: View) {
        startActivity(Intent(this, OkhttpActivity::class.java))
    }

}