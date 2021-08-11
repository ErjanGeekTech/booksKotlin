package com.example.bookskotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookskotlin.databinding.ItemBooksBinding
import com.example.bookskotlin.models.BooksModel

class BookAdapter(
    val onItemClick: (id: Int) -> Unit
) : ListAdapter<BooksModel, BookAdapter.BookViewHolder>(
    differCallback
) {

    lateinit var binding: ItemBooksBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        binding = ItemBooksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }


    inner class BookViewHolder(val binding: ItemBooksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.apply { onItemClick(absoluteAdapterPosition) }
            }
        }

        fun onBind(model: BooksModel) {
            binding.itemImageBook.setImageResource(model.image)
            binding.textTitleBook.text = model.title
        }

    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<BooksModel>() {
            override fun areItemsTheSame(
                oldItem: BooksModel,
                newItem: BooksModel
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: BooksModel,
                newItem: BooksModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}