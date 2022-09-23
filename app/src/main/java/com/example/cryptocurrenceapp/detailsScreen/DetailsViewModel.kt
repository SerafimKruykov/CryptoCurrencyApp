package com.example.cryptocurrenceapp.detailsScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrenceapp.data.Coin
import com.example.cryptocurrenceapp.data.Repository
import com.example.cryptocurrenceapp.data.api.DetailsResponseModel
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val repository = Repository()

    var coin = MutableLiveData<DetailsResponseModel>()

    fun getCoin(id: String) {
        viewModelScope.launch{
            coin.value = repository.fetchCOinDetails(id)
            Log.d("coin", coin.value.toString())
        }
    }
}