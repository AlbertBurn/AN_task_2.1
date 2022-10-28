package ru.netology.nmedia.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.myapplication.dto.Post
import ru.netology.nmedia.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    val likesDefault: Int = 17
    val sharesDefault: Int = 1099999
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = likesDefault,
            likedByMe = false,
            shares = sharesDefault
        )
        with(binding) {
            likeCount.text = changeNumber(post.likes)
            shareCount.text = changeNumber(post.shares)

            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                like?.setImageResource(R.drawable.ic_liked_24)
            }
            likeCount?.text = post.likes.toString()

            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }

            avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }

            like.setOnClickListener {
                Log.d("stuff", "like")
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likeCount.text = post.likes.toString()
            }

            share.setOnClickListener {
                Log.d("stuff", "share")
                post.shares++
                //shareCount.text = post.shares.toString()
                shareCount.text = changeNumber(post.shares)
            }
        }
    }

    //This function change number for format of app
    fun changeNumber(count: Int): String {
        val numberFirstToStr: Int
        val numberSecondToStr: Int
        if ((count >= 1_000) && (count < 10_000)) {
            numberFirstToStr = count / 1_000
            numberSecondToStr = ((count % 1_000) / 100)
            if (numberSecondToStr == 0) {
                return "$numberFirstToStr" + "K"
            } else return "$numberFirstToStr.$numberSecondToStr" + "K"
        } else if ((count >= 10_000) && (count < 1_000_000)) {
            numberFirstToStr = count / 1_000
            return "$numberFirstToStr" + "K"
        } else if (count >= 1_000_000) {
            numberFirstToStr = count / 1_000_000
            numberSecondToStr = ((count % 1_000_000) / 100_000)
            if (numberSecondToStr == 0) {
                return "$numberFirstToStr" + "M"
            } else return "$numberFirstToStr.$numberSecondToStr" + "M"
        } else {
            return "$count"
        }
    }
}

