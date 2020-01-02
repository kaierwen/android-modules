package com.github.kaierwen.androiddevlibrary.widgets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import github.kaierwen.androiddevlibrary.R
import kotlinx.android.synthetic.main.activity_circular_progress_bar.*

/**
 *
 *
 * @author kevinzhang
 * @since 2019-12-29
 */
class CircularProgressBarActivity : AppCompatActivity() {

//    override fun needButterKnife(): Boolean {
//        return true
//    }
//
//    override fun getLayoutRes(): Int {
//        return R.layout.activity_circular_progress_bar
//    }
//
//    override fun init(savedInstanceState: Bundle?) {
//        circularProgressBar.progressMax = 100f
//        circularProgressBar.setProgressWithAnimation(65f, 1000)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_progress_bar)
        circularProgressBar.setProgressWithAnimation(65f, 1000)

    }
}