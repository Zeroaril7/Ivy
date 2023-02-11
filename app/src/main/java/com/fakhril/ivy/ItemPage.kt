package com.fakhril.ivy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fakhril.ivy.adapter.ChildItemAdapter
import com.fakhril.ivy.adapter.ParentItemAdapter
import com.fakhril.ivy.adapter.PlacePageAdapter
import com.fakhril.ivy.database.Item
import com.fakhril.ivy.database.Place
import com.fakhril.ivy.viewmodel.ItemPageViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ItemPage : AppCompatActivity(){

    private var allItem = ArrayList<Item>()
    private var allPlace= ArrayList<Place>()
    private lateinit var ivyRV: RecyclerView
    private lateinit var viewModel: ItemPageViewModel
    lateinit var addFABItem: FloatingActionButton
    private val childItemAdapter = ChildItemAdapter(allItem)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_page)


        ivyRV = findViewById(R.id.parent_rv)
        addFABItem = findViewById(R.id.FAB_item)

        ivyRV.setHasFixedSize(true)
        ivyRV.layoutManager = LinearLayoutManager(this)

        val ItemPageAdapter = ParentItemAdapter(allPlace)
        ivyRV.adapter = ItemPageAdapter

        viewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ItemPageViewModel::class.java)


        viewModel.getPlace.observe(this, Observer { list ->
            list?.let{
                allPlace = it as ArrayList<Place>
                ItemPageAdapter.updateListPlace(allPlace, allItem)
            }
        })

        viewModel.getItem.observe(this, Observer { list ->
            list?.let{
                allItem = it as ArrayList<Item>
                ItemPageAdapter.updateListPlace(allPlace, allItem)
                childItemAdapter.updateListItem(it as List<Item>)
            }
        })

        addFABItem.setOnClickListener {
            val intent = Intent(this@ItemPage, AddItemPage::class.java)
            startActivity(intent)
            this.finish()
        }

        childItemAdapter.itemClickListener = object : ChildItemAdapter.ItemClickListener{
            override fun onItemClick(item: Item) {
                val intent = Intent(this@ItemPage, PreviewPlacePage::class.java)
                startActivity(intent)
                finish()
            }

        }

    }
}
