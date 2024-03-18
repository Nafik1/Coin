package com.example.myapplication.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

import com.example.myapplication.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityCoinDetailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }

        val fromsymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        if (savedInstanceState == null)  {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromsymbol.toString()))
                .commit()
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