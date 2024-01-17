package com.metromart.repositoriesapp.adapter

import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.metromart.repositoriesapp.R
import com.metromart.repositoriesapp.databinding.ItemRepositoryBinding
import com.metromart.repositoriesapp.domain.Repository
import com.metromart.repositoriesapp.extensions.capitalize


class MainAdapter(val repoItemClickListener : RepoItemClickListener) : ListAdapter<Repository, MainAdapter.RepositoryViewHolder>(diffCallBack) {

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(
            ItemRepositoryBinding.bind(view)
        )
    }

    inner class RepositoryViewHolder(private val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        val colorHighLight = binding.root.context.getColor(R.color.yellow)
        init {
            binding.root.setOnClickListener{
                repoItemClickListener.onItemClick(adapterPosition)
            }
            binding.ivDelete.setOnClickListener{
                repoItemClickListener.onDeleteClick(adapterPosition)
            }
        }
        fun bind(item: Repository) {
            with(item) {
                binding.apply {
                    val spanName: Spannable = SpannableString(name.capitalize())
                    spanName.setSpan(BackgroundColorSpan(colorHighLight), startIndex,
                        endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    val spanDescription: Spannable = SpannableString(description ?: "")
                    spanDescription.setSpan(BackgroundColorSpan(colorHighLight), startIndex,
                        endIndex,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    textName.text = spanName
                    textDescription.text = spanDescription
                }
            }
        }
    }

    companion object {
        private val diffCallBack = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Repository,
                newItem: Repository
            ): Boolean =
                oldItem == newItem
        }
    }

    interface RepoItemClickListener {
        fun onItemClick(position: Int)
        fun onDeleteClick(position: Int)
    }

}
