package com.s95ammar.loginapp.ui.choosepayment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.s95ammar.loginapp.R

class ChoosePaymentMethodFragment : Fragment() {

    companion object {
        fun newInstance() = ChoosePaymentMethodFragment()
    }

    private val viewModel: ChoosePaymentMethodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_payment_method_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
//        recyclerView.adapter =
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
