package com.s95ammar.loginapp.ui.choosepayment.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PaymentMethod(
    @DrawableRes val imageResId: Int,
    @StringRes val nameResId: Int
)
