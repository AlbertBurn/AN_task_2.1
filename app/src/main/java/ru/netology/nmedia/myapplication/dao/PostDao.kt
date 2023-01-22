package ru.netology.nmedia.myapplication.dao

import ru.netology.nmedia.myapplication.dto.Post

interface PostDao {
    fun getAll(): List<Post>
    fun save(post: Post): Post
    fun likeById(id: Long)
    fun removeById(id: Long)
    fun shareById(id: Long)
}