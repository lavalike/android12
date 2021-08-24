package com.tcl.android12

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tcl.android12.databinding.ActivitySecondBinding
import com.tcl.library.Manager

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setTheme(R.style.UiTheme)
        setContentView(ActivitySecondBinding.inflate(layoutInflater).apply {
            binding = this
        }.root)

        binding.btn.setOnClickListener {
            finish()
        }

        val msg =
            "${Manager.getTcl()}\n${Manager.getGoogle()}\n${Manager.getBaidu()}\n${Manager.getTencent()}"
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}