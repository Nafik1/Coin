package com.example.myapplication.Presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.myapplication.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinDetailFragment : Fragment() {
    private lateinit var coinviewmodel : CoinViewModel
    @Inject
    lateinit var viewmodelFactory: ViewModelFactory
    private var _binding : FragmentCoinDetailBinding? = null
    private val binding : FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("oao")

    private val component by lazy {
        (requireActivity().application as CoinApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

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
        coinviewmodel = ViewModelProvider(this,viewmodelFactory).get(CoinViewModel::class.java)
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