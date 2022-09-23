package com.example.cryptocurrenceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
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
            addToBackStack(null)
            commit()
        }
    }

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }
}