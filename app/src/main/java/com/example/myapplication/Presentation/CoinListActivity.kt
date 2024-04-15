package com.example.myapplication.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Presentation.adapters.CoinInfoAdapter
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import javax.inject.Inject

class CoinListActivity : AppCompatActivity() {

    private lateinit var coinviewmodel: CoinViewModel

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
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
        coinviewmodel = ViewModelProvider(this, viewmodelFactory).get(CoinViewModel::class.java)
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