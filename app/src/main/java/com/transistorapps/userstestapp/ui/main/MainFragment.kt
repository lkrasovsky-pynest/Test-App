package com.transistorapps.userstestapp.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.transistorapps.userstestapp.App
import com.transistorapps.userstestapp.MainActivity
import com.transistorapps.userstestapp.R
import com.transistorapps.userstestapp.databinding.FragmentMainBinding
import com.transistorapps.userstestapp.domain.UserUiModel
import com.transistorapps.userstestapp.extensions.collectWhileStarted
import com.transistorapps.userstestapp.extensions.getSnackBar
import com.transistorapps.userstestapp.extensions.navigateSafe
import com.transistorapps.userstestapp.extensions.showSnackBar
import com.transistorapps.userstestapp.extensions.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel by viewModel { App.component.mainFragmentVM }
    private lateinit var binding: FragmentMainBinding
    private val mainAdapter = MainAdapter(::onAdapterItemClick)
    private var snackBar: Snackbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        setupRefreshLayout()
        binding.recyclerView.adapter = mainAdapter
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.apply {
            viewModel.usersFlow.collectWhileStarted(viewLifecycleOwner) {
                binding.recyclerView.isVisible = it.isNotEmpty()
                mainAdapter.submitList(it)
                binding.layoutEmptyContainer.isVisible = it.isEmpty()
            }
            loading.collectWhileStarted(viewLifecycleOwner) {
                showLoading(it)
            }
            loadingFinishedEvent.collectWhileStarted(viewLifecycleOwner) {
                (activity as? MainActivity)?.dataLoaded()
            }
            errorSnackBar.collectWhileStarted(viewLifecycleOwner) {
                requireContext().showSnackBar(binding.root, getString(it.message()))
            }
        }
    }

    private fun onAdapterItemClick(userUiModel: UserUiModel) {
        findNavController().navigateSafe(MainFragmentDirections.toDetailsFragment(userUiModel.userId))
    }

    private fun setupRefreshLayout() {
        binding.srlRefresh.apply {
            setOnRefreshListener { refreshData() }
        }
    }

    private fun refreshData() {
        viewModel.syncData()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.srlRefresh.isRefreshing = isLoading
        if (isLoading) {
            snackBar =
                requireContext().getSnackBar(binding.root, getString(R.string.loadingMessage))
        } else snackBar?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        snackBar?.dismiss()
        snackBar = null
        binding.recyclerView.adapter = null
    }
}