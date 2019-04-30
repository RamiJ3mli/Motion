package com.ramijemli.motion.activity

import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.animation.PathInterpolatorCompat
import com.ramijemli.motion.R
import kotlinx.android.synthetic.main.activity_interpolator.*

class InterpolatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interpolator)

        var p = Path()
        p.lineTo(3f,3f)
        icon.animate()
                .translationYBy(300f)
                .setDuration(6000)
                .setInterpolator(PathInterpolatorCompat.create(0f, 1.95f, 0.77f, .45f))
                .setStartDelay(1000)
                .start()
    }
}
