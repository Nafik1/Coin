package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var coinviewmodel : coinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        val tvPrice = findViewById<TextView>(R.id.tvPrice)
        val logotypeCoin = findViewById<ImageView>(R.id.logotypeCoin)
        val tvFromSymmma = findViewById<TextView>(R.id.fromsymbol)
        val tvSlash = findViewById<TextView>(R.id.tvSlash)
        val tvToSymbol = findViewById<TextView>(R.id.tvToSymbol)
        val tvMinPrice = findViewById<TextView>(R.id.tvMinPrice)
        val tvMaxPrice = findViewById<TextView>(R.id.tvMaxPrice)
        val tvLastMarket = findViewById<TextView>(R.id.tvLastMarket)
        val tvLastUpdate = findViewById<TextView>(R.id.tvLastUpdate)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromsymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        coinviewmodel = ViewModelProvider(this).get(coinViewModel::class.java)
        coinviewmodel.getDetailInfo(fromsymbol.toString()).observe(this, Observer {
            Log.d("j12j3",it.toString())
            tvPrice.text = it.price
            tvMinPrice.text = it.lowday.toString()
            tvMaxPrice.text = it.highday.toString()
            tvLastMarket.text = it.lastmarket
            tvLastUpdate.text = it.getFormatedTime()
            tvToSymbol.text = it.tosymbol
            tvFromSymmma.text = it.fromsymbol
            Picasso.get().load(it.getFullUrl()).into(logotypeCoin)

        })
    }
    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newIntent(context : Context, fromsybmol : String) : Intent {
            val intent = Intent(context,CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromsybmol)
            return intent
        }
    }
}