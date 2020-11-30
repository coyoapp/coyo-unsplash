package com.coyoapp.android.unsplash.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.size.Scale
import com.coyoapp.android.unsplash.data.model.Image
import com.coyoapp.android.unsplash.databinding.ItemListImageBinding

class ImageListAdapter(
    private val imageLoader: ImageLoader,
    private val onImageClick: OnImageClick
) :
    RecyclerView.Adapter<ImageViewHolder>() {

    private val items = mutableListOf<Image>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
        ImageViewHolder(
            ItemListImageBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            imageLoader, onImageClick
        )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @Synchronized
    fun submitList(items: List<Image>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

class ImageViewHolder(
    private val binding: ItemListImageBinding,
    private val imageLoader: ImageLoader,
    private val onImageClick: OnImageClick
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Image) {
        binding.listItemImageView.load(image.thumbUrl, imageLoader) {
            scale(Scale.FIT)
            memoryCacheKey(image.id)
            crossfade(true)
        }

        binding.listItemImageView.setOnClickListener {
            onImageClick.click(image)
        }
    }
}

interface OnImageClick {
    fun click(image: Image)
}
