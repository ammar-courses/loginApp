package com.s95ammar.loginapp.util

import com.s95ammar.loginapp.R
import com.s95ammar.loginapp.ui.choosepayment.data.PaymentMethod

object Data {
    val paymentMethods = listOf(
        PaymentMethod(R.drawable.ic_cash_payment, R.string.cash),
        PaymentMethod(R.drawable.ic_credit_card, R.string.credit_card),
        PaymentMethod(R.drawable.ic_google_pay, R.string.google_pay),
        PaymentMethod(R.drawable.ic_bank_transfer, R.string.bank_transfer),
        PaymentMethod(R.drawable.ic_crypto, R.string.crypto_currency)
    )
}