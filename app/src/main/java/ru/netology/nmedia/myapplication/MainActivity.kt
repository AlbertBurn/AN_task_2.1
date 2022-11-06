package ru.netology.nmedia.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.myapplication.databinding.ActivityMainBinding
import ru.netology.nmedia.myapplication.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribe()
        setListeners()

    }

    private fun setListeners(){
        binding.like.setOnClickListener {
            viewModel.like()
        }
        binding.share.setOnClickListener {
            viewModel.share()
        }
    }

    private fun subscribe() {
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount.text = changeNumber(post.likes)
                shareCount.text = changeNumber(post.shares)
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
            }
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


