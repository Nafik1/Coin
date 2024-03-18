package com.example.myapplication.Presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cripto.API.ApiFactory

import com.example.myapplication.Domain.Domain.CoinInfo

import com.example.myapplication.Presentation.Presentation.CoinDiffCallBack
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemCoinInfoBinding
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context) :
    ListAdapter<com.example.myapplication.Domain.Domain.CoinInfo, CoinInfoAdapter.CoinInfoViewHolder>(
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
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.last_update_template)
                val symbols_template = context.resources.getString(R.string.symbols_template)
                tvSymbols.text = String.format(symbols_template, fromsymbol, tosymbol)
                tvPrice.text = price
                tvLastUpdate.text =
                    String.format(symbolsTemplate, lastupdate)
                Picasso.get().load(imageurl).into(ivLogoCoin)
            }
        }
        holder.itemView.setOnClickListener {
            onCoinClicklistener?.invoke(coin)
        }
    }


    inner class CoinInfoViewHolder(
        val binding: ItemCoinInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val ivLogoCoin = binding.ivLogoCoin
        val tvSymbols = binding.tvSymbols
        val tvPrice = binding.tvPrice
        val tvLastUpdate = binding.tvLastUpdate
    }

    interface onCoinClickListener {
        fun onCoinClick(coinInfo: CoinInfo)
    }
}