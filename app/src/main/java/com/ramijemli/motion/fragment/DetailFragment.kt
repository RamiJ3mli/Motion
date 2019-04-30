package com.ramijemli.motion.fragment


import android.content.res.ColorStateList
import android.os.Bundle
import android.transition.Explode
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.ramijemli.motion.R
import com.ramijemli.motion.model.Science
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions
import kotlinx.android.synthetic.main.fragment_detail.*


@ContainerOptions(CacheImplementation.SPARSE_ARRAY)
class DetailFragment : Fragment() {


    private var data: Science? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            data = DetailFragmentArgs.fromBundle(it).science
        }
        setupTransition()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLayout()
    }

    private fun setupTransition() {
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(activity).inflateTransition(android.R.transition.move).apply {
            duration = 400
        }

        enterTransition = Explode().apply {
            duration = 400
            interpolator = FastOutLinearInInterpolator()
        }
    }

    private fun setupLayout() {
        //CARD
        card.transitionName = data?.science + data?.color

        //ICON
        icon.transitionName = data?.science + data?.icon
        icon.backgroundTintList = ColorStateList.valueOf(data?.color!!)
        icon.setImageDrawable(context?.let { it -> ContextCompat.getDrawable(it, data?.icon!!) })

        //TITLE
        science.text = data?.science
        science.transitionName = data?.science
        startPostponedEnterTransition()
    }
}
