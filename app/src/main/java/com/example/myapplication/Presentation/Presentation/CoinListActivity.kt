package com.example.myapplication.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Presentation.Presentation.adapters.CoinInfoAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding

class CoinListActivity : AppCompatActivity() {
    private lateinit var coinviewmodel: coinViewModel
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)


        adapter.onCoinClicklistener = {
            if(binding.fragmentContainer == null) {
                launchDetailActivity(it.fromsymbol)
            } else {
                launchDetailFragment(it.fromsymbol)
            }
        }
        val CoinInforecyclerView = binding.CoinInforecyclerView
        CoinInforecyclerView.adapter = adapter
        binding.CoinInforecyclerView.itemAnimator = null
        coinviewmodel = ViewModelProvider(this).get(coinViewModel::class.java)
        coinviewmodel.ccoinInfoList.observe(this){
            adapter.submitList(it)
        }
    }
    private fun launchDetailActivity(fromSymbol: String) {
        val intent =
            CoinDetailActivity.newIntent(this@CoinListActivity, fromSymbol)
        startActivity(intent)
    }
    private fun launchDetailFragment(fromSymbol : String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

}