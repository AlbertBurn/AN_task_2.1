package ru.netology.nmedia.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.myapplication.repository.PostRepository
import ru.netology.nmedia.myapplication.repository.PostRepositoryInMemoryImpl

class PostViewModel: ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.liked()
    fun share() = repository.shared()
}