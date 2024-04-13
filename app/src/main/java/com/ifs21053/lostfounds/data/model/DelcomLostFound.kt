package com.ifs21053.lostfounds.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DelcomLostFound (
    val id: Int,
    val title: String,
    val description: String,
    val status: Boolean
) : Parcelable
