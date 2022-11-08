package ru.netology.nmedia.myapplication.dto

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 17,
    var likedByMe: Boolean = false,
    var shares: Int = 1099999
)