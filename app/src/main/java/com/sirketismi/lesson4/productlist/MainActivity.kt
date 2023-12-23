package com.sirketismi.lesson4.productlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.BaseAdapter
import com.sirketismi.lesson4.Adapters.ProductListAdapter
import com.sirketismi.lesson4.databinding.ActivityMainBinding
import com.sirketismi.lesson4.model.Product
import com.sirketismi.lesson4.newproduct.AddProductActivity

class MainActivity : AppCompatActivity() {    lateinit var binding: ActivityMainBinding
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainActivityViewModel
    lateinit var adapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setContentView(binding.root)

        adapter = ProductListAdapter(this.baseContext, mutableListOf(), onItemClick = {

        })
        binding.productListView.adapter = adapter
    }

    fun openAddProductActivity() {
        intent = Intent(this, AddProductActivity::class.java)
        //startActivity(intent)
        newProductLauncher.launch(intent)
    }

    private val newProductLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val item = result.data?.getParcelableExtra("product", Product::class.java)
            item?.let {
                adapter.addNewItem(item)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun observeAll() {
        viewModel.addProdyctObserver.observe(this) {
            if(it) {
                openAddProductActivity()
            }

        }
    }
    fun removeObservers() {
        viewModel.addProdyctObserver.removeObserver(this)
        viewModel.addProdyctObserver.postValue(false)
    }

    override fun onResume() {
        super.onResume()
        observeAll()
    }

    override fun onPause() {
        super.onPause()
    }
}