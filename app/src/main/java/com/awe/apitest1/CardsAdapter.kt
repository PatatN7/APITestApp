package com.awe.apitest1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardsAdapter(
    var cards : Cards?,
    var context : Context) : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>()

{
    var pos : Int = 0
    var onItemClick : ((Cards?) -> Unit)? =null

    class CardsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var textCardName : TextView = itemView.findViewById(R.id.textCardName)
        var textCardSet : TextView = itemView.findViewById(R.id.textCardSet)
        var textCardRarity : TextView = itemView.findViewById(R.id.textCardRarity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder
    {
        val view : View = LayoutInflater.from(context)
            .inflate(R.layout.card_list,parent,false)

        return CardsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int)
    {
        holder.textCardName.text = cards?.data?.get(position)?.name
        holder.textCardSet.text = cards?.data?.get(position)?.set?.name
        holder.textCardRarity.text = cards?.data?.get(position)?.rarity

        holder.itemView.setOnClickListener{
            pos = position
            onItemClick?.invoke(cards)
        }
    }

    override fun getItemCount(): Int
    {
        if(cards?.count == null)
        {
            return 0
        }
        else
        {
            return cards?.count!!
        }
    }
}