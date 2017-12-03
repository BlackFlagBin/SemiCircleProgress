package com.blackflagbin.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_start.setOnClickListener {
            if (et_progress.text.toString().isNotEmpty()) {
                progress.updateProgress(et_progress.text.toString().toInt())
            }
        }
    }
}
