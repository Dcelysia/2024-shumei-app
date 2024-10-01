package com.zzp.dtrip.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zzp.dtrip.R
import com.zzp.dtrip.data.StudyItem
import com.zzp.dtrip.view.CustomDialog
import com.zzp.dtrip.view.ImageDialog

class StudyAdapter(private val dataList: List<StudyItem>) :
    RecyclerView.Adapter<StudyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val textTitle: TextView = itemView.findViewById(R.id.textTitle)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        val textChapter: TextView = itemView.findViewById(R.id.chapter)
        val studyCard : LinearLayout = itemView.findViewById(R.id.studyCard)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_study, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.textTitle.text = item.title
        holder.textDescription.text = item.description
        holder.image.setImageResource(item.image)
        holder.textChapter.text = item.chapter
        holder.studyCard.setOnClickListener {
            val alertDialog = ImageDialog(holder.itemView.context,
                R.drawable.fengmian,
                "https://www.bilibili.com/video/BV1LW411s7gR/?spm_id_from=333.337.search-card.all.click&vd_source=4890f53647c8373d10d0038be543d33e")
            alertDialog.show()
        }
    }


    override fun getItemCount(): Int {
        return dataList.size
    }
}
