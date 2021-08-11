package com.example.bookskotlin.ui.fragments.detail

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


    override fun setupRequest() {
        super.setupRequest()
        args.getId.apply {
            binding.txtTitle.text = viewModel.getBook(this).title
            binding.txtDescription.text = viewModel.getBook(this).description
            binding.detailImage.setImageResource(viewModel.getBook(this).image)
        }
    }
}