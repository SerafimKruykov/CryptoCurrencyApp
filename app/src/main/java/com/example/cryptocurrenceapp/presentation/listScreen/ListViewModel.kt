package com.example.cryptocurrenceapp.presentation.listScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrenceapp.presentation.common.viewModels.SingleLiveEvent
import com.example.cryptocurrenceapp.data.models.CoinModel
import com.example.cryptocurrenceapp.data.repositories.ListRepository
import com.example.cryptocurrenceapp.data.useCases.GetEurCoinsUseCase
import com.example.cryptocurrenceapp.data.useCases.GetUsdCoinsUseCase
import kotlinx.coroutines.launch

class ListViewModel : ViewModel() {

    private val listRepository = ListRepository()

    private val getEurCoinsUseCase = GetEurCoinsUseCase(repository = listRepository)
    private val getUsdCoinsUseCase = GetUsdCoinsUseCase(repository = listRepository)

    var usdCurrencyList = MutableLiveData<List<CoinModel>?>()
    var eurCurrencyList = MutableLiveData<List<CoinModel>?>()

    val showProgressIndication = SingleLiveEvent<Unit>()
    val hideProgressIndication = SingleLiveEvent<Unit>()

    val showRefreshing = SingleLiveEvent<Unit>()
    val hideRefreshing = SingleLiveEvent<Unit>()
    val showRefreshErrorToast = SingleLiveEvent<Unit>()

    val showErrorMassage = SingleLiveEvent<Unit>()

    init {
        loadAllCoins()
    }

    private fun loadAllCoins() {
        viewModelScope.launch {

            showProgressIndication.call()

            val usdList = try {
                getUsdCoinsUseCase.execute()
            } catch (e: Exception) {
                null
            }

            val eurList = try {
                getEurCoinsUseCase.execute()
            } catch (e: Exception) {
                null
            }

            if (usdList != null && eurList != null) {
                hideProgressIndication.call()

                usdCurrencyList.value = usdList
                eurCurrencyList.value = eurList
            } else {
                hideProgressIndication.call()
                showErrorMassage.call()
            }
        }
    }

    fun refreshCoins() {
        viewModelScope.launch {
            showRefreshing.call()

            val usdList = try {
                getUsdCoinsUseCase.execute()
            } catch (e: Exception) {
                null
            }
            val eurList = try {
                getEurCoinsUseCase.execute()
            } catch (e: Exception) {
                null
            }

            if (usdList != null && eurList != null) {
                hideRefreshing.call()
                usdCurrencyList.value = usdList
                eurCurrencyList.value = eurList
            } else {
                hideRefreshing.call()
                showRefreshErrorToast.call()
            }
            hideRefreshing.call()
        }
    }

    fun tryAgain(){
        loadAllCoins()
    }
}