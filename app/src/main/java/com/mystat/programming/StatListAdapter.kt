package com.mystat.programming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mystat.databinding.ItemProgStatBinding

class StatListAdapter(
    val onItemUpdate: (statId: Long) -> Unit,
    val onItemDelete: (statId: Long) -> Unit
): ListAdapter<ProgrammingStat, StatListAdapter.StatListViewHolder>(DiffUtilProg()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatListViewHolder {
        val binding: ItemProgStatBinding = ItemProgStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatListViewHolder(binding, currentList)
    }

    override fun onBindViewHolder(holder: StatListViewHolder, position: Int) {
        val statItem = getItem(position)
        holder.bind(statItem)
    }

    inner class StatListViewHolder(private val binding: ItemProgStatBinding, private val statList: List<ProgrammingStat>)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(programmingStat: ProgrammingStat){
            binding.apply {
                containerList.setOnClickListener {
                    onItemUpdate(statList[adapterPosition].id)
                }
                containerList.setOnLongClickListener {
                    onItemDelete(statList[adapterPosition].id)
                    true
                }
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