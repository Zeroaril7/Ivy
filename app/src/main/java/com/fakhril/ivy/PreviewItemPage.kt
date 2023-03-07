package com.fakhril.ivy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fakhril.ivy.database.Item
import com.fakhril.ivy.viewmodel.ItemPageViewModel

class PreviewItemPage : AppCompatActivity(), View.OnClickListener {
    private lateinit var item_title : TextView
    private lateinit var item_name : EditText
    private lateinit var placeSpinner : Spinner
    private lateinit var total : TextView
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    private lateinit var viewModel: ItemPageViewModel
    lateinit var addBtn: ImageButton
    lateinit var removeBtn: ImageButton
    lateinit var btnToHome: ImageButton
    lateinit var btnToPlace: ImageButton
    private var place = ""
    private var oldPlace = ""
    private var oldTotalItem: Int = 0
    private var totalItem: Int = 0

    var itemID = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_item_page)
        supportActionBar?.hide()

        val oldItemName = intent.getStringExtra("itemName")
        val oldPlaceName = intent.getStringExtra("placeName")
        val oldTotal = intent.getStringExtra("total")
        itemID = intent.getIntExtra("itemId", -1)

        btnToHome = findViewById(R.id.btn_home)
        btnToPlace = findViewById(R.id.btn_place)
        addBtn = findViewById(R.id.add_button)
        removeBtn = findViewById(R.id.remove_button)
        item_title = findViewById(R.id.item_title)
        item_name = findViewById(R.id.edt_goods_name)
        placeSpinner = findViewById(R.id.action_bar_spinner)
        total = findViewById(R.id.count_item)
        btnSave = findViewById(R.id.btn_save)
        btnDelete = findViewById(R.id.btn_delete)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ItemPageViewModel::class.java)

        item_title.text = oldItemName
        item_name.setText(oldItemName)
        total.text = oldTotal
        oldPlace = oldPlaceName.toString()

        oldTotalItem = oldTotal!!.toInt()

        var spiner = ArrayAdapter<Any>(this@PreviewItemPage,
            android.R.layout.simple_spinner_item)

        spiner.add(oldPlaceName)
        viewModel.getPlace.observe(this, Observer { list ->
            list?.forEach {
                if (it.placeName != oldPlaceName) {
                    spiner.add(it.placeName)
                }
            }
        })
        spiner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        placeSpinner.adapter = spiner

        placeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?, position: Int, id: Long
            ) {
                place = placeSpinner.adapter.getItem(position) as String
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        var count = oldTotal!!.toInt()

        addBtn.setOnClickListener {
            count++
            total.setText("$count")
        }
        removeBtn.setOnClickListener{
            if(count <= 0){
                Toast.makeText(this, "Don't enter below 0", Toast.LENGTH_SHORT).show()
            } else {
                count--
                total.setText("$count")
            }
        }

        btnSave.setOnClickListener(this)
        btnDelete.setOnClickListener(this)
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
        val newItemName = item_name.text.toString()
        val oldItemName = item_title.text.toString()
        val newPlaceName = place
        val oldPlaceName = oldPlace
        val count = total.text.toString()
        totalItem = count.toInt()
        when(v?.id){
            R.id.btn_save -> {
                if(newItemName.isNotEmpty()){
                    if(newItemName == oldItemName && newPlaceName == oldPlaceName && oldTotalItem == totalItem ){
                        startActivity(Intent(applicationContext, ItemPage::class.java))
                        this.finish()
                    } else {
                        val updatedItem = Item(itemName = newItemName, total = totalItem, placeName = newPlaceName)

                        updatedItem.id = itemID
                        viewModel.updateItem(updatedItem)
                        Toast.makeText(this, "Success Update", Toast.LENGTH_LONG).show()

                        startActivity(Intent(applicationContext, ItemPage::class.java))
                        this.finish()
                    }
                }
            }
            R.id.btn_delete -> {
                val deleteItem = Item(itemName = newItemName, total = totalItem, placeName = place)
                deleteItem.id = itemID
                viewModel.deleteItem(deleteItem)

                Toast.makeText(this, "Success Delete", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, ItemPage::class.java))
                this.finish()
            }
        }
    }
}