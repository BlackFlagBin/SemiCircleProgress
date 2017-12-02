package com.blackflagbin.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zhidian.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress.updateProgress(0f)
    }
}
