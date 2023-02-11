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
import com.fakhril.ivy.database.Place

class PlacePageAdapter(
    val context: Context
) : RecyclerView.Adapter<PlacePageAdapter.PlacePageViewHolder>() {

    var ivyClickInterfacePlace : IvyClickInterfacePlace? = null

    private val allPlace = ArrayList<Place>()

    inner class PlacePageViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val card = itemView.findViewById<RelativeLayout>(R.id.rv_place_card)
        val garis = itemView.findViewById<ImageView>(R.id.line_place)
        val placeName = itemView.findViewById<TextView>(R.id.place_name)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacePageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_place_page, parent, false)
        return PlacePageViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: PlacePageViewHolder, position: Int) {
        holder.placeName.setText(allPlace.get(position).placeName)

        if (position == allPlace.size - 1){
            holder.card.setBackgroundResource(R.drawable.card_item_bottom)
            holder.garis.visibility = View.GONE
        }

        holder.itemView.setOnClickListener{
            ivyClickInterfacePlace?.onIvyClickPlace(allPlace.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allPlace.size
    }

    fun updateList(newList: List<Place>) {

        allPlace.clear()

        allPlace.addAll(newList)

        notifyDataSetChanged()
    }
    interface IvyClickInterfacePlace {
        fun onIvyClickPlace(place: Place)
    }
}



