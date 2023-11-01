package com.fortune.mvvmcleanarchitecture.ui.games.snake

import android.animation.ObjectAnimator
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fortune.mvvmcleanarchitecture.databinding.ActivitySnakeGameBinding
import com.fortune.mvvmcleanarchitecture.ui.BaseActivity
import com.fortune.mvvmcleanarchitecture.ui.NoneViewModel

class SnakeGameActivity : BaseActivity<ActivitySnakeGameBinding, NoneViewModel>(
    ActivitySnakeGameBinding::inflate,
    NoneViewModel::class
) {
    override fun ActivitySnakeGameBinding.initialize() {
        println("testtest onCreate")
        btnStartPause.setOnClickListener {
            val finalLocationAnim = ObjectAnimator.ofFloat(snake, "x", 2000f)
            finalLocationAnim.duration = 20000
            finalLocationAnim.start()
        }
    }

    private fun goRight(view: View) {
        //view.getLocationOnScreen()
    }

    private fun goLeft(view: View) {

    }

    private fun goDown(view: View) {

    }

    private fun goUp(view: View) {

    }

    override fun onDestroy() {
        super.onDestroy()
        println("testtest onDestroy")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}