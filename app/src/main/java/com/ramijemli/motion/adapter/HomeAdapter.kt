package com.ramijemli.motion.adapter


import android.content.Context
import android.content.res.ColorStateList
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ramijemli.motion.R
import com.ramijemli.motion.model.Science
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home.*


class HomeAdapter(mContext: Context?, private val listener: OnItemClickedListener) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    private var mData: SparseArray<Science>? = null

    init {
        mData = SparseArray()
        val colors = mContext?.resources?.getIntArray(R.array.home_colors)
        val titles = mContext?.resources?.getStringArray(R.array.home_titles)
        val icons = mContext?.resources?.obtainTypedArray(R.array.home_icons)

        for (i in 0 until colors?.size!!) {
            mData?.append(i, Science(titles?.get(i), colors[i], icons?.getResourceId(i, -1) ))
        }
        icons?.recycle()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
            HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false))


    override fun onBindViewHolder(viewHolder: HomeViewHolder, position: Int) {
        viewHolder.bind(mData?.get(position))
    }

    override fun getItemCount(): Int {
        return mData?.size()!!
    }

    inner class HomeViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
            LayoutContainer {

        fun bind(item: Science?) {
            card.setOnClickListener {
                listener.onItemClicked(it, card, science, icon, item)
            }
            card.transitionName = item?.science + item?.color

            science.text = item?.science
            science.transitionName = item?.science

            icon.transitionName = item?.science + item?.icon
            icon.backgroundTintList = ColorStateList.valueOf(item?.color!!)
            icon.setImageDrawable(ContextCompat.getDrawable(containerView.context, item.icon!!))
        }
    }

    interface OnItemClickedListener {
        fun onItemClicked(view: View, cardView: View, titleView: View, iconView: View, item: Science?)
    }
}
