package com.example.cryptocurrenceapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrenceapp.common.Constants.FragmentsStack.LIST_KEY
import com.example.cryptocurrenceapp.common.Fragments.Communicator
import com.example.cryptocurrenceapp.data.Coin
import com.example.cryptocurrenceapp.detailsScreen.DetailsFragment

class MainActivity : AppCompatActivity(), Communicator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun openDetails(coin: Coin?) {
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