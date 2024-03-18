package com.example.myapplication.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var coinviewmodel: coinViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)


        adapter.onCoinClicklistener = {
            val intent =
                CoinDetailActivity.newIntent(this@MainActivity, it.fromsymbol)
            startActivity(intent)
        }
        val CoinInforecyclerView = binding.CoinInforecyclerView
        CoinInforecyclerView.adapter = adapter

        coinviewmodel = ViewModelProvider(this).get(coinViewModel::class.java)
        coinviewmodel.ccoinInfoList.observe(this){
            adapter.submitList(it)
        }


    }
}