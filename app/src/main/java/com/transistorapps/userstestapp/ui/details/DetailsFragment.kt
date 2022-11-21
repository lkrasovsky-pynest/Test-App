package com.transistorapps.userstestapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.transistorapps.userstestapp.App
import com.transistorapps.userstestapp.R
import com.transistorapps.userstestapp.databinding.FragmentDetailsBinding
import com.transistorapps.userstestapp.extensions.collectWhileStarted
import com.transistorapps.userstestapp.extensions.setImage
import com.transistorapps.userstestapp.extensions.viewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val viewModel by viewModel { App.component.detailsFragmentVM }
    private lateinit var binding: FragmentDetailsBinding
    private val argsLazy: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        binding.ibBack.setOnClickListener {
            findNavController().popBackStack()
        }
        val postsAdapter = PostsAdapter()
        binding.recyclerView.adapter = postsAdapter

        viewModel.fetchUserWithPosts(argsLazy.userId)
        viewModel.user.collectWhileStarted(viewLifecycleOwner) { user ->
            user?.let { it ->
                binding.ivImage.setImage(user.url, R.drawable.ic_sync)
                postsAdapter.submitList(it.posts)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
    }
}