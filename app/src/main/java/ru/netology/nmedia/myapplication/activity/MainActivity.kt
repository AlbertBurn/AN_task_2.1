package ru.netology.nmedia.myapplication.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.myapplication.R
import ru.netology.nmedia.myapplication.adapter.OnInteractionListener
import ru.netology.nmedia.myapplication.adapter.PostsAdapter
import ru.netology.nmedia.myapplication.databinding.ActivityMainBinding
import ru.netology.nmedia.myapplication.dto.Post
import ru.netology.nmedia.myapplication.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        //функция вызываемая по завершении активити редактирования
        val editPostLauncher = registerForActivityResult(EditPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }

        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                //viewModel.shareById(post.id)
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }

            override fun onDelete(post: Post) {
                viewModel.deleteById(post.id)
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)

                editPostLauncher.launch(post.content)
            }
        })
        binding.list.adapter = adapter
        binding.list.itemAnimator = null // эта вставка должна помочь с  проблемой мерцания
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        //функция вызываемая по завершении активити создания нового поста
        val newPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
            result ?: return@registerForActivityResult
            viewModel.changeContent(result)
            viewModel.save()
        }

        viewModel.edited.observe(this) {post ->
            if (post.id == 0L) {
                return@observe
            }
        }

        binding.fab.setOnClickListener {
            newPostLauncher.launch()
        }
    }
}


