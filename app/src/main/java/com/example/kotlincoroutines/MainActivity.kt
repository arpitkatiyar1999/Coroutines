package com.example.kotlincoroutines

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlincoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding)
        {
            button.setOnClickListener {
                //Network On Main Thread Exception
//                val url=URL("https://images.unsplash.com/photo-1554080353-a576cf803bda?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cGhvdG98ZW58MHx8MHx8&w=1000&q=80")
//                val bitmap=BitmapFactory.decodeStream(url.openStream())
//                imageView.setImageBitmap(bitmap)
                CoroutineScope(Dispatchers.IO).launch {
                    Log.e("thread","Thread ${Thread.currentThread().name}")
                    val url=URL("https://images.unsplash.com/photo-1554080353-a576cf803bda?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8cGhvdG98ZW58MHx8MHx8&w=1000&q=80")
                    val bitmap=BitmapFactory.decodeStream(url.openStream())
                    withContext(Dispatchers.Main)
                    {
                        Log.e("thread","Thread ${Thread.currentThread().name}")
                        imageView.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }
}