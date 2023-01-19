package ru.netology.nmedia.myapplication.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nmedia.myapplication.R
import ru.netology.nmedia.myapplication.adapter.OnInteractionListener
import ru.netology.nmedia.myapplication.adapter.PostsAdapter
import ru.netology.nmedia.myapplication.databinding.FragmentFeedBinding
import ru.netology.nmedia.myapplication.dto.Post
import ru.netology.nmedia.myapplication.viewmodel.DataModel
import ru.netology.nmedia.myapplication.viewmodel.PostViewModel

class FeedFragment : Fragment() {
    private val dataModel : DataModel by activityViewModels()
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = PostsAdapter(object : OnInteractionListener {

            override fun onPostClick(post: Post) {
                val postClikedId = post.id
                dataModel.postIdMessage.value = postClikedId
                findNavController().navigate(R.id.action_feedFragment_to_PostFragment)
                //TODO: необходимо доделать механизм нажатия на пост и проверить.
            }

            override fun onEdit(post: Post) {
                viewModel.edit(post)
                val text = post.content
                val bundle = Bundle()
                bundle.putString("editedText", text)
                findNavController().navigate(R.id.action_feedFragment_to_editPostFragment, bundle)
            }

            override fun onLike(post: Post) {
                viewModel.likeById(post.id)
            }

            override fun onDelete(post: Post) {
                viewModel.deleteById(post.id)
            }

            override fun onShare(post: Post) {
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }

                val shareIntent =
                    Intent.createChooser(intent, getString(R.string.chooser_share_post))
                startActivity(shareIntent)
            }

            override fun onPlayVideo(post: Post) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.videoUrl))
                startActivity(intent)
            }
        })
        binding.list.adapter = adapter
        binding.list.itemAnimator = null // эта вставка должна помочь с  проблемой мерцания
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }

        return binding.root
    }
}
