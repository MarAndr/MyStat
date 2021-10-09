package com.example.mystat.programming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mystat.databinding.ItemProgStatBinding

class StatListAdapter: ListAdapter<ProgrammingStat, StatListAdapter.StatListViewHolder>(DiffUtilProg()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatListViewHolder {
        val binding: ItemProgStatBinding = ItemProgStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatListViewHolder, position: Int) {
        val statItem = getItem(position)
        holder.bind(statItem)
    }

    inner class StatListViewHolder(private val binding: ItemProgStatBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(programmingStat: ProgrammingStat){
            binding.apply {
                tvDurationValue.text = programmingStat.durationInMin.toString() + "мин"
                tvDateValue.text = programmingStat.time.toString()
                tvTypeValue.text = programmingStat.type.toString()
            }
        }
    }

    class DiffUtilProg: DiffUtil.ItemCallback<ProgrammingStat>(){
        override fun areItemsTheSame(oldItem: ProgrammingStat, newItem: ProgrammingStat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProgrammingStat,
            newItem: ProgrammingStat
        ): Boolean {
            return oldItem == newItem
        }
    }
}