package com.example.mylearningdemo.base

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mylearningdemo.R

abstract class BaseActivity : AppCompatActivity() {

    private var layoutId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        layoutId = getLayoutId()
        setContentView(layoutId)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initData()
        initView()
        initBroadCast()
        initOther()
    }


    @Override
    abstract fun initData()

    @Override
    abstract fun initView()

    @Override
    abstract fun getLayoutId(): Int

    @Override
    abstract fun initOther()

    @Override
    abstract fun initBroadCast()



}