package com.example.cryptocurrenceapp.detailsScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrenceapp.common.viewModels.SingleLiveEvent
import com.example.cryptocurrenceapp.data.Repository
import com.example.cryptocurrenceapp.data.api.DetailsResponseModel
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val repository = Repository()

    var coin = MutableLiveData<DetailsResponseModel>()

    val showProgressIndication = SingleLiveEvent<Unit>()
    val hideProgressIndication = SingleLiveEvent<Unit>()
    val showErrorLayout = SingleLiveEvent<Unit>()
    val showContent = SingleLiveEvent<Unit>()

    fun getCoin(id: String) {
        viewModelScope.launch{
            showProgressIndication.call()
            
            val requestedCoin = try {repository.fetchCOinDetails(id)}catch (e: Exception){null}
            if (requestedCoin != null){
                hideProgressIndication.call()
                showContent.call()
                coin.value = requestedCoin!!
            }else{
                hideProgressIndication.call()
                showErrorLayout.call()
            }

        }

    }
}