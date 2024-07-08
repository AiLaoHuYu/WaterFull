package com.example.mylearningdemo

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



    }



}


@GlideModule
class CustomGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        // 设置换存大小20MB
        val memoryCacheSizeBytes = 1024 * 1024 * 20 // 20MB
        // 设置内存缓存大小
        builder?.setMemoryCache(LruResourceCache(memoryCacheSizeBytes))
        // 选择内部缓存
        builder?.setDiskCache(
            InternalCacheDiskCacheFactory(
                context,
                "MyDemoTest",
                memoryCacheSizeBytes
            )
        )
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

}