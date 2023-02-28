package com.fakhril.ivy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fakhril.ivy.adapter.ItemAdapter
import com.fakhril.ivy.adapter.PlacePageAdapter
import com.fakhril.ivy.database.Item
import com.fakhril.ivy.database.IvyDao
import com.fakhril.ivy.database.IvyDatabase
import com.fakhril.ivy.database.Place
import com.fakhril.ivy.viewmodel.ItemPageViewModel
import com.fakhril.ivy.viewmodel.PlacePageViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var ivyRvPlace: RecyclerView
    lateinit var ivyRvItem: RecyclerView
    lateinit var viewModelPlace: PlacePageViewModel
    lateinit var viewModelItem: ItemPageViewModel
    lateinit var btnSeeAllPlace: TextView
    lateinit var btnSeeAllItem: TextView
    lateinit var btnToItem: ImageButton
    lateinit var btnToPlace: ImageButton
    private var allPlace = ArrayList<Place>()
    private var allItem = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        ivyRvPlace = findViewById(R.id.card_place)
        ivyRvItem = findViewById(R.id.card_item)
        btnSeeAllPlace = findViewById(R.id.btn_see_all_place)
        btnSeeAllItem = findViewById(R.id.btn_see_all_item)
        btnToItem = findViewById(R.id.btn_item)
        btnToPlace = findViewById(R.id.btn_place)

        ivyRvPlace.setHasFixedSize(true)
        ivyRvItem.setHasFixedSize(true)

        ivyRvPlace.layoutManager = LinearLayoutManager(this)
        ivyRvItem.layoutManager = LinearLayoutManager(this)

        val placeAdapter = PlacePageAdapter(this, allPlace)
        val itemAdapter = ItemAdapter(this, allItem)

        ivyRvPlace.adapter = placeAdapter
        ivyRvItem.adapter = itemAdapter

        viewModelPlace = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PlacePageViewModel::class.java)

        viewModelItem = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ItemPageViewModel::class.java)

        viewModelPlace.getPlaceLimit.observe(this, Observer {
            list -> list?.let {
            placeAdapter.updateList(it)
        }
        })

        viewModelItem.getItemLimit.observe(this, Observer {
            list -> list?.let {
                itemAdapter.updateItem(it)
        }
        })

        btnSeeAllPlace.setOnClickListener(this)
        btnSeeAllItem.setOnClickListener(this)
        btnToItem.setOnClickListener {
            val intent = Intent(this, ItemPage::class.java)
            startActivity(intent)
            this.finish()
        }
        btnToPlace.setOnClickListener{
            val intent = Intent(this, PlacePage::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_see_all_place ->{
                val intent = Intent(this, PlacePage::class.java)
                startActivity(intent)
                this.finish()
            }
            R.id.btn_see_all_item -> {
                val intent = Intent(this, ItemPage::class.java)
                startActivity(intent)
                this.finish()
            }
        }
    }
}