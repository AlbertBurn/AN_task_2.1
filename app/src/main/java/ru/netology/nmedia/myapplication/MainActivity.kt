package ru.netology.nmedia.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.myapplication.adapter.OnInteractionListener
import ru.netology.nmedia.myapplication.adapter.PostsAdapter
import ru.netology.nmedia.myapplication.databinding.ActivityMainBinding
import ru.netology.nmedia.myapplication.dto.Post
import ru.netology.nmedia.myapplication.util.AndroidUtils
import ru.netology.nmedia.myapplication.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(object : OnInteractionListener {
            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onShare(post: Post) {
                viewModel.shareById(post.id)
            }

            override fun onDelete(post: Post) {
                viewModel.deleteById(post.id)
            }

            override fun onEdit(post: Post) {
                binding.group.visibility = View.VISIBLE
                viewModel.edit(post)
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.contentEditText) {
                requestFocus()
                setText(post.content)
            }
        }
        binding.cancelEdit.setOnClickListener {
            binding.group.visibility = View.GONE // перестаёт занимать место на экране
            binding.group.visibility = View.INVISIBLE // невидима, но занимает место на экране
            with(binding.contentEditText) {
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
            }
        }

        binding.save.setOnClickListener {
            binding.group.visibility = View.GONE // перестаёт занимать место на экране
            binding.group.visibility = View.INVISIBLE // невидима, но занимает место на экране
            with(binding.contentEditText) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        context.getString(R.string.error_empty_content),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.changeContent(text.toString())
                    viewModel.save()

                    setText("")
                    clearFocus()
                    AndroidUtils.hideKeyboard(this)
                }
            }
        }
    }
}


