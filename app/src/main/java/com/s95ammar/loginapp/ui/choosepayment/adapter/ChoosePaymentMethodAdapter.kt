package com.s95ammar.loginapp.ui.choosepayment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.s95ammar.loginapp.R
import com.s95ammar.loginapp.databinding.ItemPaymentMethodBinding
import com.s95ammar.loginapp.ui.choosepayment.data.PaymentMethod

class ChoosePaymentMethodAdapter : RecyclerView.Adapter<ChoosePaymentMethodAdapter.ChoosePaymentMethodViewHolder>() {

    var list: List<PaymentMethod> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChoosePaymentMethodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_payment_method, parent, false)
        return ChoosePaymentMethodViewHolder(ItemPaymentMethodBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ChoosePaymentMethodViewHolder, position: Int) {
        list.getOrNull(position)?.let { paymentMethod ->
            holder.bind(paymentMethod)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ChoosePaymentMethodViewHolder(private val binding: ItemPaymentMethodBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PaymentMethod) {
            binding.paymentMethodImageView.setImageResource(item.imageResId)
            binding.nameTextView.text = itemView.context.getString(item.nameResId)
        }
    }

}