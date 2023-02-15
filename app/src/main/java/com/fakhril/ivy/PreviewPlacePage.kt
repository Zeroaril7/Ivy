package com.fakhril.ivy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fakhril.ivy.database.Place
import com.fakhril.ivy.viewmodel.PlacePageViewModel

class PreviewPlacePage : AppCompatActivity(), View.OnClickListener {

    private lateinit var place_name_title : TextView
    private lateinit var place_name : EditText
    private lateinit var viewModel: PlacePageViewModel
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    var placeID = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_place_page)

        val oldPlaceName = intent.getStringExtra("placeName")
        placeID = intent.getIntExtra("placeId", -1)

        place_name_title = findViewById(R.id.place_name_title)
        place_name = findViewById(R.id.edt_place_name)
        btnSave = findViewById(R.id.btn_save)
        btnDelete = findViewById(R.id.btn_delete)

        place_name_title.setText(oldPlaceName)
        place_name.setText(oldPlaceName)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PlacePageViewModel::class.java)


        btnSave.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val newPlaceName = place_name.text.toString()
        val oldPlaceName = place_name_title.text.toString()

        when (v?.id){
            R.id.btn_save -> {
                if (newPlaceName.isNotEmpty()) {

                    if(newPlaceName == oldPlaceName){
                        startActivity(Intent(applicationContext, PlacePage::class.java))
                        this.finish()
                    } else {
                        val updatedPlace = Place(placeName = newPlaceName)
                        updatedPlace.idPlace = placeID
                        viewModel.updatePlace(updatedPlace)
                        Toast.makeText(this, "Success Update", Toast.LENGTH_LONG).show()

                        startActivity(Intent(applicationContext, PlacePage::class.java))
                        this.finish()
                    }
                }
            }
            R.id.btn_delete -> {
                val deletePlace = Place(placeName = oldPlaceName)
                deletePlace.idPlace = placeID
                viewModel.deletePlace(deletePlace)

                Toast.makeText(this, "Success Delete", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, PlacePage::class.java))
                this.finish()
            }
        }
    }
}