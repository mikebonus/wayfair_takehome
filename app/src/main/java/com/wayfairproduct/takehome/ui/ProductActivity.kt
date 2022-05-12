package com.wayfairproduct.takehome.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.wayfairproduct.takehome.databinding.ActivityProductBinding
import com.wayfairproduct.takehome.util.Resource
import com.wayfairproduct.takehome.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : AppCompatActivity() {

    // insert this dependency here..
    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productAdapter = ProductAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = productAdapter
                layoutManager = LinearLayoutManager(this@ProductActivity)
            }

            viewModel.products.observe(this@ProductActivity) { result ->
                productAdapter.submitList(result.data)

                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                textViewError.text = result.error?.localizedMessage
            }

        }
    }
}