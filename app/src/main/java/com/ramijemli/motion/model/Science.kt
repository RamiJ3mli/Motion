package com.ramijemli.motion.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Science(val science: String?,
                   val color: Int?,
                   val icon: Int?) : Parcelable
