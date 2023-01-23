package ru.netology.nmedia.myapplication.repository

import androidx.lifecycle.Transformations
import ru.netology.nmedia.myapplication.dao.PostDao
import ru.netology.nmedia.myapplication.dto.Post
import ru.netology.nmedia.myapplication.entity.PostEntity

class PostRepositoryRoomImpl (
    private val dao: PostDao,
) : PostRepository {
    override fun getAll() = Transformations.map(dao.getAll()) { list ->
        list.map {
            it.toDto()
        }
    }

    override fun likeById(id: Long) {
        dao.likeById(id)
    }

    override fun shareById(id: Long) {
        dao.shareById(id)
    }

    override fun save(post: Post) {
        dao.save(PostEntity.fromDto(post))
    }

    override fun deleteById(id: Long) {
        dao.removeById(id)
    }
}