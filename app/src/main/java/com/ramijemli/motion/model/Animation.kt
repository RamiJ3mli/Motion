package com.ramijemli.motion.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Animation(val title: String?,
                     val subtitle: String?,
                     val bg: Int?,
                     val icon: Int?) : Parcelable
