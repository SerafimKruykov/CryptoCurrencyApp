package com.example.cryptocurrenceapp.listScreen

import android.content.Context
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
import com.example.cryptocurrenceapp.R
import com.example.cryptocurrenceapp.common.Constants
import com.example.cryptocurrenceapp.common.Constants.Recycler.DECIMAL_PATTERN
import com.example.cryptocurrenceapp.data.Coin
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class CoinAdapter(
    private val isUsd: Boolean,
    private val context: Context,
    private val onClick: (Coin) -> Unit
) :
    ListAdapter<Coin, CoinAdapter.CoinViewHolder>(Callback()) {

    inner class CoinViewHolder
        (itemView: View, val onClick: (Coin) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val coinName: TextView = itemView.findViewById(R.id.coinNameTextView)
        val tagName: TextView = itemView.findViewById(R.id.coinTagTextView)
        val coinCurrency: TextView = itemView.findViewById(R.id.coinCurrency)
        val coinImage: ImageView = itemView.findViewById(R.id.coinIcon)
        val coinCurrencyChange: TextView = itemView.findViewById(R.id.coinCurrencyChange)
        var currentCoin: Coin? = null

        init {
            itemView.setOnClickListener {
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
        val decimalFormat = DecimalFormat(DECIMAL_PATTERN, DecimalFormatSymbols((Locale.ENGLISH)))
        val coin: Coin = getItem(position)

        holder.coinName.text = coin.name
        holder.tagName.text = coin.symbol!!.uppercase()

        if (isUsd) {
            holder.coinCurrency.text = context.getString(
                R.string.usd_currency_str,
                decimalFormat.format(coin.current_price?.toDouble())
            )
        } else {
            holder.coinCurrency.text =
                context.getString(
                    R.string.eur_currency_str,
                    decimalFormat.format(coin.current_price?.toDouble()))
        }

        holder.coinCurrencyChange.apply {
            if (coin.price_change_percentage_24h!!.toDouble() > 0.0) {
                text =
                    String.format(
                        context.getString(
                            R.string.positive_change,
                            coin.price_change_percentage_24h.toDouble()
                        )
                    )
            } else if (coin.price_change_percentage_24h.toDouble() < 0.0) {
                text =
                    String.format(
                        context.getString(
                            R.string.currency_change,

                            coin.price_change_percentage_24h.toDouble()
                        )
                    )
                setTextColor(Color.parseColor(Constants.Recycler.RED_COLOR))
                rootView.findViewById<TextView>(R.id.percent)
                    .setTextColor(Color.parseColor(Constants.Recycler.RED_COLOR))
            } else {
                text =
                    String.format(
                        context.getString(
                            R.string.currency_change,
                            coin.price_change_percentage_24h.toDouble()
                        )
                    )
            }
        }

        holder.coinImage.load(coin.image) {
            transformations(CircleCropTransformation())
        }
        holder.currentCoin = coin
    }

    class Callback : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.name == newItem.name
        }
    }
}