package com.coyoapp.android.unsplash.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.coyoapp.android.unsplash.data.model.Image
import com.coyoapp.android.unsplash.databinding.FragmentImageListBinding
import org.koin.androidx.viewmodel.scope.viewModel
import org.koin.ext.scope

class ImageListFragment : Fragment(), OnImageClick {

    private val viewModel: ImageListViewModel by scope.viewModel(owner = this)
    private val imageListAdapter: ImageListAdapter by scope.inject()

    private lateinit var binding: FragmentImageListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentImageListBinding.inflate(inflater, container, false).let {
        binding = it
        it.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imagesRecyclerView.apply {
            adapter = imageListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.getImages().observe(viewLifecycleOwner, {
            imageListAdapter.submitList(it)
        })
    }

    override fun click(image: Image) {
        findNavController().navigate(ImageListFragmentDirections.openDetail(image.id))
    }
}
