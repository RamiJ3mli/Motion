package com.ramijemli.motion.fragment

import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ramijemli.motion.R
import com.ramijemli.motion.activity.InterpolatorActivity
import com.ramijemli.motion.adapter.HomeAdapter
import kotlinx.android.extensions.CacheImplementation
import kotlinx.android.extensions.ContainerOptions
import kotlinx.android.synthetic.main.fragment_home.*


@ContainerOptions(CacheImplementation.SPARSE_ARRAY)
class HomeFragment : Fragment(), HomeAdapter.OnItemClickedListener {

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTransitions()
        setupRV()
    }

    private fun setupTransitions() {
        postponeEnterTransition()
        exitTransition = Explode().apply {
            duration = 400
            interpolator = FastOutLinearInInterpolator()
        }
    }

    private fun setupRV() {
        val llm = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        llm.isItemPrefetchEnabled = true
        sciencesRv.layoutManager = llm
        sciencesRv.setHasFixedSize(true)
        sciencesRv.setItemViewCacheSize(10)
        adapter = HomeAdapter(context, this)
        sciencesRv.adapter = adapter
        sciencesRv.doOnLayout {
            startPostponedEnterTransition()
        }
    }

    override fun onItemClicked(position: Int) {
        when(position){
            0 -> startActivity(Intent(activity, InterpolatorActivity::class.java))
        }

//        Navigation.findNavController(view).navigate(
//                HomeFragmentDirections.gotoDetail(item),
//                FragmentNavigatorExtras(
//                        cardView to cardView.transitionName,
//                        iconView to iconView.transitionName,
//                        titleView to titleView.transitionName
//                )
//        )
    }
}
