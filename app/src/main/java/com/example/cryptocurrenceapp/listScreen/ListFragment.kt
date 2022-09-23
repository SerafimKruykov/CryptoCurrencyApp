package com.example.cryptocurrenceapp.listScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrenceapp.Communicator
import com.example.cryptocurrenceapp.R
import com.example.cryptocurrenceapp.data.Coin
import com.example.cryptocurrenceapp.databinding.FragmentListBinding


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var communicator: Communicator
    private lateinit var viewModel: ListViewModel

    private val coinAdapterUsd = CoinAdapter(true) { coin -> recyclerCallback(coin) }
    private val coinAdapterEur = CoinAdapter(false) { coin -> recyclerCallback(coin) }

    private var binding: FragmentListBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        communicator = activity as Communicator
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        subscribeToViewModel()
    }

    private fun subscribeToViewModel() {
        viewModel.usdCurrencyList.observe(this) {
            coinAdapterUsd.submitList(it)
        }
        viewModel.eurCurrencyList.observe(this) {
            coinAdapterEur.submitList(it)
        }
    }

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

        coinAdapterUsd.submitList(viewModel.usdCurrencyList.value)

        binding?.recycler?.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = coinAdapterUsd
        }

        binding?.chipEur?.setOnClickListener {
            binding?.recycler?.adapter = coinAdapterEur
        }
        binding?.chipUsd?.setOnClickListener {
            binding?.recycler?.adapter = coinAdapterUsd
        }
    }

    private fun recyclerCallback(coin: Coin?) {
        communicator.openDetails(coin)
    }
}