package com.example.cryptocurrenceapp.detailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.text.trimmedLength
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.example.cryptocurrenceapp.R
import com.example.cryptocurrenceapp.common.Constants.DetailsFragment.ARG_KEY
import com.example.cryptocurrenceapp.common.Fragments.Communicator
import com.example.cryptocurrenceapp.data.Coin
import com.example.cryptocurrenceapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var communicator: Communicator
    private lateinit var coin: Coin

    private var binding: FragmentDetailsBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        communicator = activity as Communicator

        arguments?.let {
            coin = it.getParcelable(ARG_KEY)!!
        }

        viewModel.getCoin(coin.id!!)
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

    private fun subscribeToViewModel() {

        viewModel.coin.observe(this) {

            binding?.apply {

                descriptionContent.text =
                    HtmlCompat.fromHtml(it.description.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
                        .substring(15,
                            HtmlCompat.fromHtml(it.description.toString(),
                                HtmlCompat.FROM_HTML_MODE_LEGACY
                            ).trimmedLength() - 1
                        )

                categoriesContent.text =
                    it.categories.toString()
                        .substring(1, it.categories.toString().trimmedLength() - 1)
            }
        }

        viewModel.showProgressIndication.observe(this) {
            binding?.progressIndicator?.visibility = View.VISIBLE
        }

        viewModel.hideProgressIndication.observe(this) {
            binding?.progressIndicator?.visibility = View.GONE
        }

        viewModel.showErrorLayout.observe(this) {
            binding?.errorLayout?.visibility = View.VISIBLE
        }

        viewModel.showContent.observe(this) {
            binding?.contentGroup?.visibility = View.VISIBLE
        }
    }

    private fun initView() {
        binding?.apply {
            coinNameTextView2.text = coin.name

            coinIcon2.load(coin.image) {
                transformations(CircleCropTransformation())
            }

            tryAgainBtn2.setOnClickListener {
                viewModel.getCoin(coin.id!!)
                binding?.errorLayout?.visibility = View.GONE
            }

        }.also {
            it?.goBackBtn?.setOnClickListener {
                communicator.goBack()
            }
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