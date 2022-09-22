package com.example.cryptocurrenceapp.listScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrenceapp.R
import com.example.cryptocurrenceapp.data.Coin
import com.example.cryptocurrenceapp.databinding.FragmentListBinding


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var coinAdapter: CoinAdapter
    private var binding: FragmentListBinding? = null


    override fun onStart() {
        super.onStart()
        initView()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun initView() {
        coinAdapter = CoinAdapter { coin -> recyclerCallback(coin) }.apply {
            submitList(mutableListOf(
                Coin("Fima", "FM", "null", "12345", "123", "123","123","123","123")))
        }
        binding?.recycler?.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = coinAdapter
        }
    }
    fun recyclerCallback(coin: Coin?){}

}