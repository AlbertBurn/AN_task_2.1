package ru.netology.nmedia.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.myapplication.databinding.CardPostBinding
import ru.netology.nmedia.myapplication.dto.Post

//typealias OnLikeListener = (post: Post) -> Unit

interface OnInteractionListener {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
//    fun onMenu(post: Post) {}
//    fun onSaveListener(post: Post) {}
//    fun onReadListener(post: Post) {}
    fun onEdit(post: Post) {}
    fun onDelete(post: Post) {}
}

class PostsAdapter( private val onInteractionListener: OnInteractionListener)
    : ListAdapter<Post, PostViewHolder>(PostDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

//    override fun getItemCount(): Int = currentList.size
}

class PostDiffCallBack : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}