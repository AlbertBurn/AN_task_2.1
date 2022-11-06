package ru.netology.nmedia.myapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.myapplication.MainActivity
import ru.netology.nmedia.myapplication.R
import ru.netology.nmedia.myapplication.databinding.ActivityMainBinding
import ru.netology.nmedia.myapplication.dto.Post

class PostRepositoryInMemoryImpl: PostRepository {
    //val binding = ActivityMainBinding.inflate()

    private val likesDefault: Int = 17
    private val sharesDefault: Int = 1099999
    private var post = Post (
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likes = likesDefault,
        likedByMe = false,
        shares = sharesDefault
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data

    override fun liked() {
        Log.d("stuff", "like")
        post = post.copy(likedByMe = !post.likedByMe)
        if (post.likedByMe) post.likes++ else post.likes--
        data.value = post
    }

    override fun shared() {
        Log.d("stuff", "share")
        post.shares++
        //shareCount.text = changeNumber(post.shares)
        data.value = post
    }
}