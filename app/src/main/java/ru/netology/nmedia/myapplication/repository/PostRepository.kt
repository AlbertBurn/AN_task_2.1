package ru.netology.nmedia.myapplication.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.myapplication.dto.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun liked()
    fun shared()
}