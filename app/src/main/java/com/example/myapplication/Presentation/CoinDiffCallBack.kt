package com.example.myapplication.Presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.Domain.CoinInfo

object CoinDiffCallBack : DiffUtil.ItemCallback<CoinInfo>(){
    override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem.fromsymbol == newItem.fromsymbol
    }

    override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean {
        return oldItem == newItem
    }
}