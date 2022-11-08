package ru.netology.nmedia.myapplication.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.myapplication.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)
}