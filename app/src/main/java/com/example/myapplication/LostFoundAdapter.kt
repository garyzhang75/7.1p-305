package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemLostFoundBinding

class LostFoundAdapter(
    private val onItemClick: (LostFoundItem) -> Unit
) : ListAdapter<LostFoundItem, LostFoundAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemLostFoundBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LostFoundItem) {
            binding.textViewTitle.text = item.name
            binding.textViewDescription.text = item.description

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<LostFoundItem>() {
        override fun areItemsTheSame(oldItem: LostFoundItem, newItem: LostFoundItem): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LostFoundItem, newItem: LostFoundItem): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLostFoundBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
