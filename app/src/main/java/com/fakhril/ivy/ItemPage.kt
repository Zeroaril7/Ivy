package com.fakhril.ivy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fakhril.ivy.adapter.ParentItemAdapter
import com.fakhril.ivy.adapter.PlacePageAdapter
import com.fakhril.ivy.database.Item
import com.fakhril.ivy.viewmodel.ItemPageViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ItemPage : AppCompatActivity(){

    private lateinit var ivyRV: RecyclerView
    private lateinit var viewModel: ItemPageViewModel
    private lateinit var addFABItem: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_page)


        ivyRV = findViewById(R.id.parent_rv)

        ivyRV.setHasFixedSize(true)
        ivyRV.layoutManager = LinearLayoutManager(this)

        val ItemPageAdapter = ParentItemAdapter(this)

        ivyRV.adapter = ItemPageAdapter

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ItemPageViewModel::class.java)


        viewModel.getPlace.observe(this, Observer { list ->
            list?.let{
                ItemPageAdapter.updateListPlace(it)
            }
        })
    }

}
