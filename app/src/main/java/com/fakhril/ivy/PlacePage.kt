package com.fakhril.ivy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fakhril.ivy.adapter.PlacePageAdapter
import com.fakhril.ivy.database.Place
import com.fakhril.ivy.viewmodel.PlacePageViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class PlacePage : AppCompatActivity() {
    lateinit var ivyRV: RecyclerView
    lateinit var viewModel: PlacePageViewModel
    lateinit var addFABPlace: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_page)


        ivyRV = findViewById(R.id.card)
        addFABPlace = findViewById(R.id.FAB_place)


        ivyRV.setHasFixedSize(true)
        ivyRV.layoutManager = LinearLayoutManager(this)

        val placePageAdapter = PlacePageAdapter(this)


        ivyRV.adapter = placePageAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PlacePageViewModel::class.java)

        viewModel.getPlace.observe(this, Observer { list ->
            list?.let{
                placePageAdapter.updateList(it)
            }
        })

        addFABPlace.setOnClickListener {
            val intent = Intent(this@PlacePage, AddPlacePage::class.java)
            startActivity(intent)
            this.finish()
        }

        placePageAdapter.ivyClickInterfacePlace = object : PlacePageAdapter.IvyClickInterfacePlace{
            override fun onIvyClickPlace(place: Place) {
                val intent = Intent(this@PlacePage, PreviewPlacePage::class.java)
                intent.putExtra("placeId", place.idPlace)
                intent.putExtra("placeName", place.placeName)
                startActivity(intent)
                finish()
            }

        }
    }
}