package com.example.istu_mobile_lab2

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Artwork(
    @DrawableRes val imageResId: Int,
    @StringRes val titleResId: Int,
    @StringRes val authorResId: Int
)