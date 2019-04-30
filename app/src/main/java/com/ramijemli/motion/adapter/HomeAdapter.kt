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
import com.ramijemli.motion.model.Animation
import com.ramijemli.motion.model.Science
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_home.*
import kotlinx.android.synthetic.main.item_home.card
import kotlinx.android.synthetic.main.item_home.icon
import kotlinx.android.synthetic.main.item_home.view.*


class HomeAdapter(mContext: Context?, private val listener: OnItemClickedListener) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


    private var mData: SparseArray<Animation>? = null

    init {
        mData = SparseArray()
        val titles = mContext?.resources?.getStringArray(R.array.home_titles)
        val subtitles = mContext?.resources?.getStringArray(R.array.home_subtitles)
        val icons = mContext?.resources?.obtainTypedArray(R.array.home_icons)
        val bgs = mContext?.resources?.obtainTypedArray(R.array.home_bgs)

        for (i in 0 until titles?.size!!) {
            mData?.append(i, Animation(titles[i], subtitles?.get(i), bgs?.getResourceId(i, -1), icons?.getResourceId(i, -1) ))
        }
        icons?.recycle()
        bgs?.recycle()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
            HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false))

    override fun onBindViewHolder(viewHolder: HomeViewHolder, position: Int) = viewHolder.bind(mData?.get(position))

    override fun getItemCount(): Int = mData?.size()!!

    inner class HomeViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: Animation?) {
            card.apply {
                background = ContextCompat.getDrawable(containerView.context, item?.bg!!)
                setOnClickListener {
                    listener.onItemClicked(adapterPosition)
                }
            }

            number.text = (adapterPosition + 1).toString()
            title.text = item?.title
            subtitle.text = item?.subtitle
        }
    }

    interface OnItemClickedListener {
        fun onItemClicked(position: Int)
    }
}
