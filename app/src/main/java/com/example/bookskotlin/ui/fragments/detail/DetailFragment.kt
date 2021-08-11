package com.example.bookskotlin.ui.fragments.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookskotlin.R
import com.example.bookskotlin.base.BaseFragment
import com.example.bookskotlin.databinding.FragmentDetailBinding
import com.example.bookskotlin.ui.fragments.home.DescriptionViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding, DescriptionViewModel>(
    R.layout.fragment_detail
) {
    override val binding by viewBinding(FragmentDetailBinding::bind)
    val args: DetailFragmentArgs by navArgs()
    override val viewModel: DescriptionViewModel by activityViewModels()

    var id: Int? = null

    override fun initialize() {
        super.initialize()
        getIdBook()
    }

    private fun getIdBook() {
        id = args.getId
    }

    override fun setupRequest() {
        super.setupRequest()
        id?.let {
            binding.txtTitle.text = viewModel.getBook(it).title
            binding.txtDescription.text = viewModel.getBook(it).description
            binding.detailImage.setImageResource(viewModel.getBook(it).image)
        }
    }
}