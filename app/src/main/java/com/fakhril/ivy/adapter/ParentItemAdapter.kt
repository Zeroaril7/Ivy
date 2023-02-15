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

class ParentItemAdapter(private val allPlace: ArrayList<Place>): RecyclerView.Adapter<ParentItemAdapter.ParentItemViewHolder>() {

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
        val place = allPlace[position]

        holder.placeName.text = place.placeName

        holder.ivyRV.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(holder.itemView.context)

            var itemList = ArrayList<Item>()
            itemList.clear()
            for (item in allItem) {
                if(item.placeName == place.placeName){
                    itemList.add(item)
                }
            }

            val adapter = ChildItemAdapter(context, itemList)
            holder.ivyRV.adapter = adapter
        }


    }

    override fun getItemCount(): Int {
       return allPlace.size
    }

    fun updateListPlace(newList: List<Place>, newListItem: List<Item>) {

        allPlace.clear()
        allPlace.addAll(newList)

        allItem.clear()
        allItem.addAll(newListItem)

        notifyDataSetChanged()
    }
}