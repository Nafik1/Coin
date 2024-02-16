package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cripto.API.ApiFactory
import com.example.cripto.pojo.CoinInfo
import com.example.cripto.pojo.CoinPriceInfo
import com.example.myapplication.Adapter.CoinInfoAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var coinviewmodel : coinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClicklistener = object : CoinInfoAdapter.onCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(this@MainActivity, coinPriceInfo.fromsymbol)
                startActivity(intent)
            }
        }
        val CoinInforecyclerView = findViewById<RecyclerView>(R.id.CoinInforecyclerView)
        CoinInforecyclerView.adapter = adapter

        coinviewmodel = ViewModelProvider(this).get(coinViewModel::class.java)
        coinviewmodel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })



    }
}