package com.example.cryptocurrenceapp.listScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrenceapp.data.Coin
import androidx.recyclerview.widget.DiffUtil
import com.example.cryptocurrenceapp.R

class CoinAdapter(private val onClick: (Coin) -> Unit):
    ListAdapter<Coin, CoinAdapter.CoinViewHolder>(Callback()) {

    inner class CoinViewHolder
        (itemView: View, val onClick: (Coin) -> Unit)
        : RecyclerView.ViewHolder(itemView){
            val coinName: TextView = itemView.findViewById(R.id.coinNameTextView)
            val tagName: TextView = itemView.findViewById(R.id.coinTagTextView)
            val coinCurrency: TextView = itemView.findViewById(R.id.coinCurrency)
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
        holder.tagName.text = coin.tag
        holder.coinCurrency.text = coin.currency
        holder.coinCurrencyChange.text = coin.currencyChange
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