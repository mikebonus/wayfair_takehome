package com.wayfairproduct.takehome.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wayfairproduct.takehome.R
import com.wayfairproduct.takehome.data.Product
import com.wayfairproduct.takehome.databinding.ProductItemBinding

class ProductAdapter : ListAdapter<Product, ProductAdapter.RestaurantViewHolder>(
    RestaurantComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class RestaurantViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(product: Product) {
                binding.apply {

                    // Image
                    Glide.with(itemView)
                        .load(R.drawable.wayfair)
                        .into(imageViewLogo)

                    // Name
                    textViewName.text = product.name

                    // Description
                    textViewTagline.text = product.tagline

                    // Rating (1 decimal place)
                    val random = product.rating
                    val roundoff = String.format("%.1f", random)
                    textViewRating.text = roundoff
                }
            }
    }

    class RestaurantComparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Product, newItem: Product) =
            oldItem == newItem
    }
}