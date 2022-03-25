package com.nghiemtuananh.baitapappdocbaot3h

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.nghiemtuananh.baitapappdocbaot3h.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {
    lateinit var binding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web)
        var url = intent.getStringExtra("url")
        binding.wvBaibao.loadUrl(url!!)
    }
}