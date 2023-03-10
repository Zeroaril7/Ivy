package com.fakhril.ivy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.fakhril.ivy.database.Item
import com.fakhril.ivy.database.Place
import com.fakhril.ivy.viewmodel.ItemPageViewModel
import com.fakhril.ivy.viewmodel.PlacePageViewModel

class PreviewPlacePage : AppCompatActivity(), View.OnClickListener {

    private lateinit var place_name_title : TextView
    private lateinit var place_name : EditText
    private lateinit var viewModel: PlacePageViewModel
    private lateinit var viewModelItem : ItemPageViewModel
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    lateinit var btnToItem: ImageButton
    lateinit var btnToHome: ImageButton

    var placeID = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_place_page)
        supportActionBar?.hide()

        val oldPlaceName = intent.getStringExtra("placeName")
        placeID = intent.getIntExtra("placeId", -1)

        btnToItem = findViewById(R.id.btn_item)
        btnToHome = findViewById(R.id.btn_home)
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

        viewModelItem = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ItemPageViewModel::class.java)


        btnSave.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
        btnToItem.setOnClickListener {
            val intent = Intent(this, ItemPage::class.java)
            startActivity(intent)
            this.finish()
        }
        btnToHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
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
                
                viewModelItem.deleteSelectedItem(oldPlaceName)

                Toast.makeText(this, "Success Delete", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, PlacePage::class.java))
                this.finish()
            }
        }
    }
}