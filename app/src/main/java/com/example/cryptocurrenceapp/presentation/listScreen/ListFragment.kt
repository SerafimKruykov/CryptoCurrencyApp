package com.example.cryptocurrenceapp.presentation.listScreen

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrenceapp.presentation.common.Fragments.Communicator
import com.example.cryptocurrenceapp.R
import com.example.cryptocurrenceapp.presentation.common.Constants.ListFragment.GRAVITY_X
import com.example.cryptocurrenceapp.presentation.common.Constants.ListFragment.GRAVITY_Y
import com.example.cryptocurrenceapp.data.models.CoinModel
import com.example.cryptocurrenceapp.databinding.FragmentListBinding


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var communicator: Communicator
    private lateinit var viewModel: ListViewModel

    private lateinit var coinAdapterUsd: CoinAdapter
    private lateinit var coinAdapterEur: CoinAdapter

    private var binding: FragmentListBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        communicator = activity as Communicator
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        subscribeToViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        initView()
    }

    private fun subscribeToViewModel() {
        viewModel.usdCurrencyList.observe(this) {
            coinAdapterUsd.submitList(it)
        }
        viewModel.eurCurrencyList.observe(this) {
            coinAdapterEur.submitList(it)
        }
        viewModel.hideProgressIndication.observe(this) {
            binding?.progressIndicator?.visibility = View.GONE
        }
        viewModel.showProgressIndication.observe(this) {
            binding?.progressIndicator?.visibility = View.VISIBLE
        }
        viewModel.showErrorMassage.observe(this) {
            binding?.errorLayout?.visibility = View.VISIBLE
        }
        viewModel.showRefreshing.observe(this) {
            binding?.swipeLayout?.isRefreshing = true
        }
        viewModel.hideRefreshing.observe(this) {
            binding?.swipeLayout?.isRefreshing = false
        }
        viewModel.showRefreshErrorToast.observe(this) {
            showErrorToast()
        }
    }

    private fun initView() {

        coinAdapterUsd = CoinAdapter(true, resources) { coin -> recyclerCallback(coin) }.apply {
            submitList(viewModel.usdCurrencyList.value)
        }
        coinAdapterEur = CoinAdapter(false, resources) { coin -> recyclerCallback(coin) }.apply{
            submitList(viewModel.eurCurrencyList.value)
        }

        binding?.apply {
            recycler.apply {
                layoutManager = LinearLayoutManager(context)
                this.adapter = if (chipUsd.isChecked) coinAdapterUsd else coinAdapterEur
            }
            chipEur.setOnClickListener {
                coinAdapterEur.submitList(viewModel.eurCurrencyList.value)
                binding?.recycler?.adapter = coinAdapterEur
            }
            chipUsd.setOnClickListener {
                coinAdapterUsd.submitList(viewModel.usdCurrencyList.value)
                binding?.recycler?.adapter = coinAdapterUsd
            }
            tryAgainBtn.setOnClickListener {
                viewModel.tryAgain()
                binding?.errorLayout?.visibility = View.GONE
            }
            swipeLayout.setOnRefreshListener {
                viewModel.refreshCoins()
            }
        }
    }

    private fun showErrorToast() {
        Toast(context).apply {
            view = layoutInflater.inflate(R.layout.toast_layout, null)
            setGravity(Gravity.BOTTOM, GRAVITY_X, GRAVITY_Y)
            duration = Toast.LENGTH_SHORT
        }.show()
    }

    private fun recyclerCallback(coin: CoinModel?) {
        communicator.openDetails(coin)
    }
}