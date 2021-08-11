package com.example.bookskotlin.ui.fragments.home

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookskotlin.R
import com.example.bookskotlin.base.BaseFragment
import com.example.bookskotlin.databinding.FragmentHomeBinding
import com.example.bookskotlin.ui.adapters.BookAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding, DescriptionViewModel>(
    R.layout.fragment_home
) {
    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: DescriptionViewModel by activityViewModels()
    val bookAdapter: BookAdapter = BookAdapter(this::onItemClick)

    override fun setupViews() {
        super.setupViews()
        setupRecycler()
    }

    private fun setupRecycler() {
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bookAdapter
        }
    }

    override fun setupListener() {
        super.setupListener()
        setupClickButtonStart()
    }


    private fun setupClickButtonStart() {
        binding.btnStart.setOnClickListener{
            binding.btnStart.visibility = View.GONE
            binding.rv.visibility = View.VISIBLE
            viewModel.getList()
        }
    }

    override fun setupRequest() {
        super.setupRequest()
        fetchBook()
    }

    private fun fetchBook() {
        viewModel.fetchBook.observe(viewLifecycleOwner, {
            binding.btnStart.visibility = View.GONE
            binding.rv.visibility = View.VISIBLE
            bookAdapter.addList(it)
        })
    }

    private fun onItemClick(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id))
    }


}