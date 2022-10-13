package com.example.cryptocurrenceapp.presentation.detailsScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrenceapp.presentation.common.viewModels.SingleLiveEvent
import com.example.cryptocurrenceapp.data.models.DetailsResponseModel
import com.example.cryptocurrenceapp.data.repositories.DetailsRepository
import com.example.cryptocurrenceapp.data.useCases.GetCoinDetailsUseCase
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val getUsdCoinsUseCase = GetCoinDetailsUseCase(repository = DetailsRepository())

    var coin = MutableLiveData<DetailsResponseModel?>()

    val showProgressIndication = SingleLiveEvent<Unit>()
    val hideProgressIndication = SingleLiveEvent<Unit>()
    val showErrorLayout = SingleLiveEvent<Unit>()
    val showContent = SingleLiveEvent<Unit>()

    fun getCoin(id: String) {
        viewModelScope.launch{
            showProgressIndication.call()
            
            val requestedCoin = try {getUsdCoinsUseCase.execute(id = id)}catch (e: Exception){null}
            if (requestedCoin != null){
                hideProgressIndication.call()
                showContent.call()
                coin.value = requestedCoin
            }else{
                hideProgressIndication.call()
                showErrorLayout.call()
            }

        }

    }
}