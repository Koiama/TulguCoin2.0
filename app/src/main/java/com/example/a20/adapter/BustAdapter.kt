package com.example.a20.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a20.R
import com.example.a20.bust.Bust

class BustAdapter(var counter : Int): RecyclerView.Adapter<BustAdapter.BustViewHolder>() {

    var data: List<Bust> = emptyList()
        set(newValue){
            field = newValue
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BustViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.bust_item, parent, false)
        return BustViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: BustViewHolder, position: Int) {
        data[position].let{
            val bust = it

            holder.bustImage.setImageResource(bust.imageResId)
            holder.bustName.text = bust.name
            holder.bustLvl.text = "${it.count} lvl"
            // Обновляем цену при инициализации
            holder.bustPrice.text = (it.price * it.count).toString()


            holder.bustLvl.setOnClickListener {
                if (counter> bust.price) {
                    bust.count++
                    holder.bustLvl.text = "${bust.count} lvl"
                    holder.bustPrice.text = (bust.price * bust.count).toString()// Обновляем текст для цены
                    counter=counter-bust.price
                }
            }

        }

    }

    class BustViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bustImage: ImageView= itemView.findViewById(R.id.bustImage)
        val bustName: TextView = itemView.findViewById(R.id.bustName)
        val bustLvl: Button = itemView.findViewById(R.id.bustButton)
        val bustPrice: TextView = itemView.findViewById(R.id.bustPrice) // Убедитесь, что это правильно
    }
}
