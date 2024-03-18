package com.example.myapplication.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.myapplication.databinding.ActivityCoinDetailBinding
import com.example.myapplication.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailFragment : Fragment() {
    private lateinit var coinviewmodel : coinViewModel
    private var _binding : FragmentCoinDetailBinding? = null
    private val binding : FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("oao")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = requireArguments()
        val fromsymbol = args.getString(EXTRA_FROM_SYMBOL)
        coinviewmodel = ViewModelProvider(this).get(coinViewModel::class.java)
        coinviewmodel.getDetailInfo(fromsymbol.toString()).observe(viewLifecycleOwner){
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        fun newInstance(fromsybmol : String) : CoinDetailFragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromsybmol)
                }
            }
        }

    }
}