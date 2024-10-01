package com.zzp.dtrip.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zzp.dtrip.R
import com.zzp.dtrip.data.ForumItemData
import com.zzp.dtrip.view.CustomDialog

class ForumPostAdapter(private val posts: List<ForumItemData>) : RecyclerView.Adapter<ForumPostAdapter.ForumPostViewHolder>() {

    inner class ForumPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postCardItem : CardView = itemView.findViewById(R.id.postCardItem)
        val imageViewComment: ImageView = itemView.findViewById(R.id.imageViewComment)
        val imageViewFavorite: ImageView = itemView.findViewById(R.id.imageViewFavorite)
        val imageViewLike: ImageView = itemView.findViewById(R.id.imageViewLike)
        val likeNum: TextView = itemView.findViewById(R.id.likeNum)
        val commentNum: TextView = itemView.findViewById(R.id.commentNum)
        val favoriteNum: TextView = itemView.findViewById(R.id.favoriteNum)
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val contentTextView: TextView = itemView.findViewById(R.id.textViewContent)
        val authorTimeTextView: TextView = itemView.findViewById(R.id.textViewAuthorTime)
        val avatarImageView: ImageView = itemView.findViewById(R.id.imageViewAvatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumPostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forum_post, parent, false)
        return ForumPostViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForumPostViewHolder, position: Int) {
        val post = posts[position]
        holder.titleTextView.text = post.title
        holder.contentTextView.text = post.content
        holder.authorTimeTextView.text = "${post.author} • ${post.time}"
        holder.avatarImageView.setImageResource(R.drawable.ic_face)
        holder.likeNum.text = post.likeNum
        holder.commentNum.text = post.commentNum
        holder.favoriteNum.text = post.favoriteNum
//        holder.imageViewLike.setOnClickListener {
//            animateIcon(holder.imageViewLike)
//            // 处理点赞逻辑
//            Toast.makeText(holder.itemView.context, "点赞", Toast.LENGTH_SHORT).show()
//        }
//
//        holder.imageViewComment.setOnClickListener {
//            animateIcon(holder.imageViewComment)
//            // 处理评论逻辑
//            Toast.makeText(holder.itemView.context, "评论", Toast.LENGTH_SHORT).show()
//        }
//
//        holder.imageViewFavorite.setOnClickListener {
//            animateIcon(holder.imageViewFavorite)
//            // 处理收藏逻辑
//            Toast.makeText(holder.itemView.context, "收藏", Toast.LENGTH_SHORT).show()
//        }
        holder.postCardItem.setOnClickListener {
            val alertDialog = CustomDialog(holder.itemView.context)
            alertDialog.show("因相关政策及备案需求，论坛功能关闭中")
        }
    }
    private fun animateIcon(imageView: ImageView) {
        val scaleAnimation = ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 200
        scaleAnimation.repeatCount = 1
        scaleAnimation.repeatMode = Animation.REVERSE
        imageView.startAnimation(scaleAnimation)
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}
