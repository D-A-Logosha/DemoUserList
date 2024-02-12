package com.example.demouserlist.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    @DrawableRes
    val image: Int
) : Parcelable