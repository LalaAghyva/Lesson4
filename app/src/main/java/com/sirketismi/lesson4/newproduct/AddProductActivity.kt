package com.sirketismi.lesson4.newproduct

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sirketismi.lesson4.databinding.ActivityAddProductBinding
import com.sirketismi.lesson4.databinding.ActivityMainBinding
import com.sirketismi.lesson4.productlist.MainActivityViewModel


class AddProductActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddProductBinding
    lateinit var viewModel : AddProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(AddProductViewModel::class.java)

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onResume() {
        super.onResume()
        observerAll()
    }

    fun observerAll() {
        viewModel.newProductCallback.observe(this) {
            val intent = Intent()
            val product = viewModel.createNewProduct()
            intent.putExtra("product", product)


            setResult(RESULT_OK, intent)
            finish()
        }

        viewModel.errorDescription.observe(this) {
            if(!it.isNullOrEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}