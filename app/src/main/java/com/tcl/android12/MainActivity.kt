package com.tcl.android12

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.ViewTreeObserver
import android.window.SplashScreenView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd

/**
 * MainActivity
 * Created by wangzhen on 2021/8/10
 */
class MainActivity : AppCompatActivity() {
    companion object {
        const val DURATION = 2000
    }

    private val initTime = SystemClock.uptimeMillis()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        splashDelay()

        splashExit()
    }

    private fun splashExit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // 默认退出效果
//                 splashScreenView.remove()

                // 缩小出屏幕
//                 scaleExit(splashScreenView)

                // 向上平移出屏幕
                slideUp(splashScreenView)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun slideUp(view: SplashScreenView) {
        val iconView = view.iconView ?: return
        AnimatorSet().apply {
            playSequentially(
                ObjectAnimator.ofFloat(iconView, View.TRANSLATION_Y, 0f, 50f),
                ObjectAnimator.ofFloat(iconView, View.TRANSLATION_Y, 50f, -view.height.toFloat()),
            )
            doOnEnd { view.remove() }
            start()
        }

    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun scaleExit(view: SplashScreenView) {
        ObjectAnimator.ofFloat(view, View.SCALE_X, View.SCALE_Y, Path().apply {
            moveTo(1f, 1f)
            lineTo(0f, 0f)
        }).apply {
            doOnEnd { view.remove() }
            start()
        }
    }

    private fun splashDelay() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                return if ((SystemClock.uptimeMillis() - initTime) > DURATION) {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    true
                } else false
            }
        })
    }
}