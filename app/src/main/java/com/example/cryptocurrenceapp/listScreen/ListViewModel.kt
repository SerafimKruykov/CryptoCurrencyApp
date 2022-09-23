package com.example.cryptocurrenceapp.listScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrenceapp.data.Coin
import com.example.cryptocurrenceapp.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    private val repository = Repository()

    var usdCurrencyList = MutableLiveData<List<Coin>>()
    var eurCurrencyList = MutableLiveData<List<Coin>>()

    init {
        loadAllCoins()
    }

    private fun loadAllCoins() {
        viewModelScope.launch{
            usdCurrencyList.value = repository.fetchUsdCurrency()
            eurCurrencyList.value = repository.fetchEurCurrency()
        }
    }

    fun refreshCoins(){
        loadAllCoins()
    }
}