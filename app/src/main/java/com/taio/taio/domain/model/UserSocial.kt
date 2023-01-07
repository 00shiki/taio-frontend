package com.taio.taio.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserSocial(
    val follower: List<User> = emptyList(),
    val following: List<User> = emptyList(),
): Parcelable
