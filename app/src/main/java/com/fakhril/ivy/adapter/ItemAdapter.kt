package com.fakhril.ivy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fakhril.ivy.R
import com.fakhril.ivy.database.Item
import com.fakhril.ivy.database.Place

class ItemAdapter(val context: Context,  val allItem : ArrayList<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardItem = itemView.findViewById<RelativeLayout>(R.id.rv_item_card)
        val lineItem = itemView.findViewById<ImageView>(R.id.line_item)
        val itemName = itemView.findViewById<TextView>(R.id.item_name)
        val placeItemName = itemView.findViewById<TextView>(R.id.item_place_name)
        val itemCount = itemView.findViewById<TextView>(R.id.item_count)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_page, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var itemList = allItem[position]

        holder.itemName.text = itemList.itemName
        holder.placeItemName.text = itemList.placeName
        holder.itemCount.text = itemList.total.toString()

        if (position == allItem.size -1){
            holder.cardItem.setBackgroundResource(R.drawable.card_item_bottom)
            holder.lineItem.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return allItem.size
    }

    fun updateItem(newList: List<Item>) {

        allItem.clear()

        allItem.addAll(newList)

        notifyDataSetChanged()
    }
}