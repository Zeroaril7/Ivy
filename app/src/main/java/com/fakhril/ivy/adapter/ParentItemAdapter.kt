package com.fakhril.ivy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fakhril.ivy.R
import com.fakhril.ivy.database.Item

import com.fakhril.ivy.database.Place

class ParentItemAdapter(val context: Context): RecyclerView.Adapter<ParentItemAdapter.ParentItemViewHolder>() {

    private val allPlace = ArrayList<Place>()
    private val Item = ArrayList<Item>()
    private val allItem = ArrayList<Item>()




    inner class ParentItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val placeName = itemView.findViewById<TextView>(R.id.place_name_item)
        val ivyRV = itemView.findViewById<RecyclerView>(R.id.child_rv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.parent_rv_item, parent, false)

        return ParentItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ParentItemViewHolder, position: Int) {
        var place = allPlace[position]

        holder.placeName.setText(allPlace.get(position).placeName)
        holder.ivyRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(holder.itemView.context)

//          Di adapter bawah ini pada bagian Item, perlu position dari place. Akan tetapi pada bagian Item belum ada position dari place
            holder.ivyRV.adapter = ChildItemAdapter(context, Item)
        }
    }

    override fun getItemCount(): Int {
       return allPlace.size
    }

    fun updateListPlace(newList: List<Place>) {

        allPlace.clear()

        allPlace.addAll(newList)

        notifyDataSetChanged()
    }

    fun updateListItem(newList: List<Item>){
        allItem.clear()

        allItem.addAll(newList)

        notifyDataSetChanged()
    }
}