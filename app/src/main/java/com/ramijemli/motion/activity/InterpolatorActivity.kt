package com.ramijemli.motion.activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramijemli.motion.adapter.InterpolatorAdapter
import kotlinx.android.synthetic.main.activity_interpolator.*


class InterpolatorActivity : AppCompatActivity() {

    private lateinit var adapter: InterpolatorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.ramijemli.motion.R.layout.activity_interpolator)
        setupRV()
    }

    private fun setupRV() {
        val llm = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
        llm.isItemPrefetchEnabled = true
        interRv.layoutManager = llm
        interRv.setHasFixedSize(true)
        adapter = InterpolatorAdapter(baseContext)
        interRv.adapter = adapter
    }
}
