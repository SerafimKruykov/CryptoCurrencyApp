package com.example.cryptocurrenceapp.listScreen

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cryptocurrenceapp.Constants
import com.example.cryptocurrenceapp.R
import com.example.cryptocurrenceapp.data.Coin

class CoinAdapter(private val isUsd: Boolean, private val onClick: (Coin) -> Unit):
    ListAdapter<Coin, CoinAdapter.CoinViewHolder>(Callback()) {

    inner class CoinViewHolder
        (itemView: View, val onClick: (Coin) -> Unit)
        : RecyclerView.ViewHolder(itemView){
        val coinName: TextView = itemView.findViewById(R.id.coinNameTextView)
        val tagName: TextView = itemView.findViewById(R.id.coinTagTextView)
        val coinCurrency: TextView = itemView.findViewById(R.id.coinCurrency)
        val coinImage: ImageView = itemView.findViewById(R.id.coinIcon)
        val coinCurrencyChange: TextView = itemView.findViewById(R.id.coinCurrencyChange)
        var currentCoin: Coin? = null

        init {
            itemView.setOnClickListener{
                currentCoin?.let {
                    onClick(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.coin_item, parent, false), onClick
        )
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin: Coin = getItem(position)
        holder.coinName.text = coin.name
        holder.tagName.text = coin.symbol
        if(isUsd == true){
            holder.coinCurrency.text = "$" + coin.current_price
        }else{
            holder.coinCurrency.text = "€" + coin.current_price
        }

        holder.coinCurrencyChange.apply {
            if(coin.price_change_percentage_24h!!.toDouble()>0.0){
                text = "+" + coin.price_change_percentage_24h
            }else if(coin.price_change_percentage_24h.toDouble()<0.0){
                text = coin.price_change_percentage_24h
                setTextColor(Color.parseColor(Constants.Recycler.RED_COLOR))
            }else {
                text = coin.price_change_percentage_24h
            }
        }

        holder.coinImage.load(coin.image){
            transformations(CircleCropTransformation())
        }
        holder.currentCoin = coin
    }

    class Callback: DiffUtil.ItemCallback<Coin>(){
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.name == newItem.name
        }
    }
}