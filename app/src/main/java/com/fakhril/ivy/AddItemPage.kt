package com.fakhril.ivy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fakhril.ivy.database.Item
import com.fakhril.ivy.viewmodel.ItemPageViewModel
import com.fakhril.ivy.viewmodel.PlacePageViewModel

class AddItemPage : AppCompatActivity(), View.OnClickListener{
    lateinit var viewModel: ItemPageViewModel
    lateinit var viewModelPlace : PlacePageViewModel
    lateinit var placeSpinner: Spinner
    lateinit var addBtn: ImageButton
    lateinit var removeBtn: ImageButton
    lateinit var saveBtn: TextView
    lateinit var totalItem: TextView
    lateinit var btnClose: ImageButton
    lateinit var edtItem: TextView
    lateinit var btnToHome: ImageButton
    lateinit var btnToPlace: ImageButton
    private var place = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_page)
        supportActionBar?.hide()

        addBtn = findViewById(R.id.add_button)
        removeBtn = findViewById(R.id.remove_button)
        saveBtn = findViewById(R.id.text_save)
        totalItem = findViewById(R.id.count_item)
        btnClose = findViewById(R.id.close)
        placeSpinner = findViewById(R.id.action_bar_spinner)
        edtItem = findViewById(R.id.edt_goods_name)
        btnToHome = findViewById(R.id.btn_home)
        btnToPlace = findViewById(R.id.btn_place)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ItemPageViewModel::class.java)



        var spiner = ArrayAdapter<Any>(this@AddItemPage,
            android.R.layout.simple_spinner_item)

        spiner.add("Pilih")
        viewModel.getPlace.observe(this, Observer { list ->
            list?.forEach {
                spiner.add(it.placeName)
            }
        })
        spiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        placeSpinner.adapter = spiner

        placeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
              place = placeSpinner.adapter.getItem(position) as String
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        var count = 0
        addBtn.setOnClickListener {
            count++
            totalItem.setText("$count")
        }
        removeBtn.setOnClickListener{
            if(count <= 0){
                Toast.makeText(this, "Don't enter below 0", Toast.LENGTH_SHORT).show()
            } else {
                count--
                totalItem.setText("$count")
            }
        }
        saveBtn.setOnClickListener(this)
        btnClose.setOnClickListener(this)
        btnToHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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
        val itemName = edtItem.text.toString()
        val total = totalItem.text.toString()
        val count = total.toInt()
        when(v?.id){
            R.id.text_save -> {
                if (place == "Pilih" || itemName.isEmpty() || count.equals(0)){
                    Toast.makeText(this, "Enter The Right Value", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.insertItem(Item(itemName = itemName, total = count, placeName = place))
                    Toast.makeText(this, "$itemName Added", Toast.LENGTH_LONG).show()

                    startActivity(Intent(applicationContext, ItemPage::class.java))
                    this.finish()
                }
            }
            R.id.close -> {
                startActivity(Intent(applicationContext, ItemPage::class.java))
                this.finish()
            }
        }
    }
}