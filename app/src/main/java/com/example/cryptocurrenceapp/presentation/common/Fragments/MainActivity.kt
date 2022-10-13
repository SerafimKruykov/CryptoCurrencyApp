package com.example.cryptocurrenceapp.presentation.common.Fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrenceapp.R
import com.example.cryptocurrenceapp.presentation.common.Constants.FragmentsStack.LIST_KEY
import com.example.cryptocurrenceapp.data.models.CoinModel
import com.example.cryptocurrenceapp.presentation.detailsScreen.DetailsFragment

class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun openDetails(coin: CoinModel?) {
        val detailsFragment = DetailsFragment.newInstance(coin)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, detailsFragment)
            addToBackStack(LIST_KEY)
            commit()
        }
    }

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }
}