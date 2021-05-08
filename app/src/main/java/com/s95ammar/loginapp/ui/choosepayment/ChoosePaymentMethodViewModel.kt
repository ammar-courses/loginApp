package com.s95ammar.loginapp.ui.choosepayment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.s95ammar.loginapp.ui.choosepayment.data.PaymentMethod
import com.s95ammar.loginapp.util.Data

class ChoosePaymentMethodViewModel : ViewModel() {

    private val _paymentMethods = MutableLiveData<List<PaymentMethod>>()

    val paymentMethods: LiveData<List<PaymentMethod>> = _paymentMethods

    init {
        setPaymentMethods()
    }

    private fun setPaymentMethods() {
        _paymentMethods.value = Data.paymentMethods
    }
}