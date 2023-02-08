package com.fakhril.ivy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.fakhril.ivy.database.Place
import com.fakhril.ivy.viewmodel.PlacePageViewModel

class AddPlacePage : AppCompatActivity(), View.OnClickListener {
    lateinit var btnClose: ImageButton
    lateinit var btnSave : TextView
    lateinit var edtPlaceName: EditText
    lateinit var viewModel: PlacePageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place_page)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(PlacePageViewModel::class.java)

        btnClose = findViewById(R.id.close)
        btnSave = findViewById(R.id.text_save)
        edtPlaceName = findViewById(R.id.edt_place_name)

        btnSave.setOnClickListener(this)
        btnClose.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        val placeName = edtPlaceName.text.toString()

        when (v?.id){
            R.id.text_save -> {

                if(placeName.isEmpty()){
                    edtPlaceName.setError("Field tidak boleh kosong")
                } else {
                    viewModel.insertPlace(Place(placeName = placeName))
                    Toast.makeText(this, "$placeName Added", Toast.LENGTH_LONG).show()

                    startActivity(Intent(applicationContext, PlacePage::class.java))
                    this.finish()
                }
            }

            R.id.close -> {
                startActivity(Intent(applicationContext, PlacePage::class.java))
                this.finish()
            }
        }

    }
}