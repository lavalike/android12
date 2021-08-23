package com.tcl.android12

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tcl.library.Manager

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setTheme(R.style.UiTheme)
        setContentView(R.layout.activity_second)

        findViewById<View>(R.id.btn).setOnClickListener {
            finish()
        }

        val msg = "${Manager.getTcl()}\n${Manager.getGoogle()}"
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}