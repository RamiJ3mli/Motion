package com.ramijemli.motion.adapter


import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.core.view.animation.PathInterpolatorCompat
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.ramijemli.motion.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_interpolator.*


const val LINEAR = 0
const val ACCELERATE = 1
const val DECELERATE = 2
const val ACCELERATE_DECELERATE = 3
const val ANTICIPATE = 4
const val OVERSHOOT = 5
const val ANTICIPATE_OVERSHOOT = 6
const val BOUNCE = 7
const val FAST_OUT_LINEAR_IN = 8
const val FAST_OUT_SLOW_IN = 9
const val LINEAR_OUT_SLOW_IN = 10
const val CUSTOM = 11


class InterpolatorAdapter(mContext: Context?) : RecyclerView.Adapter<InterpolatorAdapter.InterpolatorViewHolder>() {


    private var mData: SparseArray<String>? = null

    init {
        mData = SparseArray(12)
        mData?.apply {
            val titles = mContext?.resources?.getStringArray(R.array.interpolator_titles)
            for (i in 0 until titles?.size!!) {
                mData?.append(i, titles[i])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterpolatorViewHolder =
            InterpolatorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_interpolator, parent, false))

    override fun onBindViewHolder(viewHolder: InterpolatorViewHolder, position: Int) = viewHolder.bind(position)

    override fun getItemCount(): Int = mData?.size()!!

    inner class InterpolatorViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(position: Int) {
            chart.setInterpolator(
                    when (position) {
                        LINEAR -> LinearInterpolator()
                        ACCELERATE -> AccelerateInterpolator()
                        DECELERATE -> DecelerateInterpolator()
                        ACCELERATE_DECELERATE -> AccelerateDecelerateInterpolator()
                        ANTICIPATE -> AnticipateInterpolator()
                        OVERSHOOT -> OvershootInterpolator()
                        ANTICIPATE_OVERSHOOT -> AnticipateOvershootInterpolator()
                        BOUNCE -> BounceInterpolator()
                        FAST_OUT_LINEAR_IN -> FastOutLinearInInterpolator()
                        FAST_OUT_SLOW_IN -> FastOutSlowInInterpolator()
                        LINEAR_OUT_SLOW_IN -> LinearOutSlowInInterpolator()
                        CUSTOM -> PathInterpolatorCompat.create(.61f, 1.42f, .45f, -.59f)
                        else -> LinearInterpolator()
                    }
            )
            title.text = mData?.get(adapterPosition)
        }
    }
}
