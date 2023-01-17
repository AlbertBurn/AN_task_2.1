package ru.netology.nmedia.myapplication.activity

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.myapplication.R
import ru.netology.nmedia.myapplication.activity.Companion.Companion.longArg
import ru.netology.nmedia.myapplication.databinding.FragmentPostBinding
import ru.netology.nmedia.myapplication.viewmodel.PostViewModel

class PostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostBinding.inflate(layoutInflater)

        val viewModel: PostViewModel by viewModels(
            ownerProducer = ::requireParentFragment
        )

        with(binding.scrollContent) {
            viewModel.data.observe(viewLifecycleOwner) { posts ->
                val post = posts.find { it.id == arguments?.longArg }
                if (post != null) {
                    author.text = post.author
                    published.text = post.published
                    content.text = post.content
                    like.isChecked = post.likedByMe
                    like.text = changeNumber(post.likes)
                    share.text = changeNumber(post.shares)


                    if (post.videoUrl != null) {
                        this.videoLayout.visibility = View.VISIBLE
                        videoView.apply {
                            setVideoURI(Uri.parse(post.videoUrl))
                            requestFocus()
                            start()
                        }
                    } else {
                        videoLayout.visibility = View.GONE
                    }

                    like.setOnClickListener {
                        viewModel.likeById(post.id)
                    }

                    share.setOnClickListener {
                        viewModel.shareById(post.id)
                    }

                    menu.setOnClickListener {
                        PopupMenu(it.context, it).apply {
                            inflate(R.menu.options_post)
                            setOnMenuItemClickListener { item ->
                                when (item.itemId) {
                                    R.id.remove -> {
                                        findNavController().navigateUp()
                                        viewModel.deleteById(post.id)
                                        true
                                    }
                                    R.id.edit -> {
                                        viewModel.edit(post)
                                        findNavController().navigate(R.id.action_postFragment_to_editPostFragment)
                                        true
                                    }

                                    else -> false
                                }
                            }
                        }.show()
                    }
                }
            }
        }
        return binding.root
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