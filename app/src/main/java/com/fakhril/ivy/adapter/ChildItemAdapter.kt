package com.fakhril.ivy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fakhril.ivy.PreviewItemPage
import com.fakhril.ivy.R
import com.fakhril.ivy.database.Item

class ChildItemAdapter(private val context: Context,
    private val allItem: ArrayList<Item>,
): RecyclerView.Adapter<ChildItemAdapter.ChildItemViewHoder>() {


    var itemClickListener: ChildItemAdapter.ItemClickListener? = null

    inner class ChildItemViewHoder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cardItem = itemView.findViewById<RelativeLayout>(R.id.rv_child_item)
        val garisItem = itemView.findViewById<ImageView>(R.id.line_item)
        val itemName = itemView.findViewById<TextView>(R.id.item_name)
        val itemCount = itemView.findViewById<TextView>(R.id.item_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildItemViewHoder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.child_rv_item, parent, false)
        return ChildItemViewHoder(itemView)
    }

    override fun onBindViewHolder(holder: ChildItemViewHoder, position: Int) {
        var itemList = allItem[position]
        holder.itemName.text = itemList.itemName
        holder.itemCount.text = itemList.total.toString()
        if (position == allItem.size - 1){
            holder.cardItem.setBackgroundResource(R.drawable.card_item_bottom)
            holder.garisItem.visibility = View.GONE
        }

        holder.itemView.setOnClickListener(){
            val intent = Intent(context, PreviewItemPage::class.java)
            itemClickListener?.onItemClick(itemList)
            intent.putExtra("itemId", itemList.id)
            intent.putExtra("itemName", itemList.itemName)
            intent.putExtra("placeName", itemList.placeName)
            intent.putExtra("total", itemList.total.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return allItem.size
    }

    fun updateListItem(newList: List<Item>){
        allItem.clear()

        allItem.addAll(newList)

        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(item: Item)
    }
}