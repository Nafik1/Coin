package com.example.myapplication.Adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cripto.pojo.CoinInfo
import com.example.cripto.pojo.CoinPriceInfo
import com.example.myapplication.R
import com.squareup.picasso.Picasso


class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList : List<CoinPriceInfo> = arrayListOf<CoinPriceInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onCoinClicklistener : onCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList.get(position)
        with(holder) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.last_update_template)
                val symbols_template = context.resources.getString(R.string.symbols_template)
                tvSymbols.text = String.format(symbols_template, fromsymbol, tosymbol)
                tvPrice.text = price
                tvLastUpdate.text = String.format(symbolsTemplate,getFormatedTime())
                Picasso.get().load(getFullUrl()).into(ivLogoCoin)
            }
        }
        holder.itemView.setOnClickListener {
            onCoinClicklistener?.onCoinClick(coin)
        }
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin = itemView.findViewById<ImageView>(R.id.ivLogoCoin)
        val tvSymbols = itemView.findViewById<TextView>(R.id.tvSymbols)
        val tvPrice = itemView.findViewById<TextView>(R.id.tvPrice)
        val tvLastUpdate = itemView.findViewById<TextView>(R.id.tvLastUpdate)
    }
    interface onCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}