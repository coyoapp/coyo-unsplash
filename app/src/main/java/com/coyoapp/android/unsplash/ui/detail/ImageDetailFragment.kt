package com.coyoapp.android.unsplash.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.ImageLoader
import coil.request.ImageRequest
import com.coyoapp.android.unsplash.databinding.FragmentImageDetailBinding
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.ext.scope

class ImageDetailFragment : Fragment() {

    private val viewModel: ImageDetailViewModel by scope.viewModel(owner = this)
    private val imageLoader: ImageLoader by scope.inject()
    private val args: ImageDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentImageDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentImageDetailBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getImage(args.imageId).observe(viewLifecycleOwner, {
            imageLoader.enqueue(
                ImageRequest.Builder(view.context)
                    .data(it.regularUrl)
                    .placeholderMemoryCacheKey(it.id)
                    .crossfade(true)
                    .target(binding.detailImage)
                    .build()
            )

            binding.detailImageDesc.text = it.description ?: it.alt_description
            binding.detailImageBio.text = it.bio
        })

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    findNavController().navigateUp()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    companion object {
        const val TAG = "ImageDetailFragment"
    }
}
