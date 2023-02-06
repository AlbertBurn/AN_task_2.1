package ru.netology.nmedia.myapplication.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.myapplication.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long): Post
    fun shareById(id: Long)

//    fun menuById(id: Long)
    fun save(post: Post)

//    fun edit(post: Post)
    fun deleteById(id: Long)

    fun getById(id: Long): Post
}