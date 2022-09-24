package com.example.cryptocurrenceapp.listScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrenceapp.SingleLiveEvent
import com.example.cryptocurrenceapp.data.Coin
import com.example.cryptocurrenceapp.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val repository = Repository()

    var usdCurrencyList = MutableLiveData<List<Coin>>()
    var eurCurrencyList = MutableLiveData<List<Coin>>()

    val showProgressIndication = SingleLiveEvent<Unit>()
    val hideProgressIndication = SingleLiveEvent<Unit>()
    val showErrorMassage = SingleLiveEvent<Unit>()

    init {
        loadAllCoins()
    }

    private fun loadAllCoins() {
        viewModelScope.launch {

            showProgressIndication.call()

            val usdList = try{repository.fetchUsdCurrency()}catch (e: Exception){null}
            val eurList = try{repository.fetchEurCurrency()}catch (e: Exception){null}

            if (usdList != null && eurList != null) {
                hideProgressIndication.call()

                usdCurrencyList.value = usdList!!
                eurCurrencyList.value = eurList!!
            }else{
                hideProgressIndication.call()
                showErrorMassage.call()
            }
        }
    }

    fun refreshCoins() {
        loadAllCoins()
    }
}