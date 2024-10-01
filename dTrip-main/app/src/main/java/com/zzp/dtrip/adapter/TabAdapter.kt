package com.zzp.dtrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zzp.dtrip.R

class TabAdapter(private val data: List<String>) : RecyclerView.Adapter<TabAdapter.TabViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tab, parent, false)
        return TabViewHolder(view)
    }

    override fun onBindViewHolder(holder: TabViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class TabViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textViewItem)

        fun bind(item: String) {
            textView.text = item
        }
    }
}