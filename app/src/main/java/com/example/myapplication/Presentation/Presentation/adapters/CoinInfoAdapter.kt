package com.example.myapplication.Presentation.Presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.myapplication.Domain.Domain.CoinInfo

import com.example.myapplication.Presentation.Presentation.CoinDiffCallBack
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemCoinInfoBinding
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context) :
    ListAdapter<com.example.myapplication.Domain.Domain.CoinInfo, CoinInfoViewHolder>(
        CoinDiffCallBack()
    ) {


    var onCoinClicklistener: ((CoinInfo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        return CoinInfoViewHolder(
            ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.last_update_template)
                val symbols_template = context.resources.getString(R.string.symbols_template)
                tvSymbols.text = String.format(symbols_template, fromsymbol, tosymbol)
                tvPrice.text = price
                tvLastUpdate.text =
                    String.format(symbolsTemplate, lastupdate)
                Picasso.get().load(imageurl).into(ivLogoCoin)
                root.setOnClickListener {
                    onCoinClicklistener?.invoke(coin)
                }
            }
        }

    }




    interface onCoinClickListener {
        fun onCoinClick(coinInfo: CoinInfo)
    }
}