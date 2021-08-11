package com.example.bookskotlin.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookskotlin.databinding.ItemBooksBinding
import com.example.bookskotlin.models.BooksModel

class BookAdapter(
    val onItemClick: (id: Int) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    var list: ArrayList<BooksModel> = ArrayList()
    lateinit var binding: ItemBooksBinding

    fun addList(getList: ArrayList<BooksModel>) {
        list = getList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        binding = ItemBooksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        list[position].let { holder.onBind(it) }
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class BookViewHolder(val binding: ItemBooksBinding) : RecyclerView.ViewHolder(binding.root) {


        fun onBind(model: BooksModel) {
            binding.itemImageBook.setImageResource(model.image)
            binding.textTitleBook.text = model.title
        }

    }
}