package com.s95ammar.loginapp.ui.choosepayment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.s95ammar.loginapp.R

class ChoosePaymentMethodAdapter : RecyclerView.Adapter<ChoosePaymentMethodAdapter.ChoosePaymentMethodViewHolder>() {

    var list: List<Any/*TODO: fix*/> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChoosePaymentMethodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main/*TODO: fix*/, parent, false)
        return ChoosePaymentMethodViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChoosePaymentMethodViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }


    class ChoosePaymentMethodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}