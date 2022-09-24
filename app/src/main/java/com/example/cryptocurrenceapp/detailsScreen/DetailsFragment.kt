package com.example.cryptocurrenceapp.detailsScreen

import android.os.Bundle
import android.text.Html
import android.text.Html.fromHtml
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.trimmedLength
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cryptocurrenceapp.Communicator
import com.example.cryptocurrenceapp.Constants.DetailsFragment.ARG_KEY
import com.example.cryptocurrenceapp.R
import com.example.cryptocurrenceapp.data.Coin
import com.example.cryptocurrenceapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var binding: FragmentDetailsBinding? = null
    private lateinit var viewModel: DetailsViewModel
    private lateinit var communicator: Communicator

    private lateinit var coin: Coin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        communicator = activity as Communicator

        arguments?.let {
            coin = it.getParcelable(ARG_KEY)!!
        }

        viewModel.getCoin(coin.id!!)
    }

    private fun subscribeToViewModel() {
        viewModel.coin.observe(this) {
            binding?.apply {

                this.descriptionContent.text =
                    fromHtml(it.description.toString())
                        .substring(15, fromHtml(it.description.toString()).trimmedLength() - 1)

                this.categoriesContent.text =
                    it.categories.toString()
                        .substring(1, it.categories.toString().trimmedLength() - 1)
            }
        }

        viewModel.showProgressIndication.observe(this){
            binding?.progressIndicator?.visibility = View.VISIBLE
        }

        viewModel.hideProgressIndication.observe(this){
            binding?.progressIndicator?.visibility = View.GONE
        }

        viewModel.showErrorLayout.observe(this){
            binding?.errorLayout?.visibility = View.VISIBLE
        }

        viewModel.showContent.observe(this){
            binding?.contentGroup?.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        initView()
        subscribeToViewModel()
    }

    fun initView(){
        binding?.apply {
            coinNameTextView2.text = coin.name
            coinIcon2.load(coin.image) {
                transformations(CircleCropTransformation())
            }
        }.also{
            it?.goBackBtn?.setOnClickListener{
                communicator.goBack()
            }
        }
        binding?.tryAgainBtn2?.setOnClickListener{
            viewModel.getCoin(coin.id!!)
            binding?.errorLayout?.visibility = View.GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(coin: Coin?) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_KEY, coin!!)
                }
            }
    }
}