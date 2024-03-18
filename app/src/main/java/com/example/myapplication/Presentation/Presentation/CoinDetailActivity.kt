package com.example.myapplication.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.myapplication.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var coinviewmodel : coinViewModel
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        val tvPrice = binding.tvPrice
//        val logotypeCoin = binding.logotypeCoin
//        val tvFromSymmma = binding.fromsymbol
//        val tvSlash = binding.tvSlash
//        val tvToSymbol = binding.tvToSymbol
//        val tvMinPrice = binding.tvMinPrice
//        val tvMaxPrice = binding.tvMaxPrice
//        val tvLastMarket = binding.tvLastMarket
//        val tvLastUpdate = binding.tvLastUpdate

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }

        val fromsymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        coinviewmodel = ViewModelProvider(this).get(coinViewModel::class.java)
        coinviewmodel.getDetailInfo(fromsymbol.toString()).observe(this){
            with(binding) {
                tvPrice.text = it.price
                tvMinPrice.text = it.lowday.toString()
                tvMaxPrice.text = it.highday.toString()
                tvLastMarket.text = it.lastmarket
                tvLastUpdate.text = it.lastupdate
                tvToSymbol.text = it.tosymbol
                binding.fromsymbol.text = it.fromsymbol
                Picasso.get().load(it.imageurl).into(logotypeCoin)
            }

        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(context : Context, fromsybmol : String) : Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromsybmol)
            return intent
        }

    }
}