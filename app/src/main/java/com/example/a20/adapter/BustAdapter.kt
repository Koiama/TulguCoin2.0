package com.example.a20.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.recyclerview.widget.RecyclerView
import com.example.a20.R
import com.example.a20.bust.Bust
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "bust_data") // создание файла для хранения данных

class BustAdapter(
    private val context: Context,
    private val coroutineScope: CoroutineScope,
    var counter: Int
) : RecyclerView.Adapter<BustAdapter.BustViewHolder>() {

    var data: List<Bust> = emptyList() // создание элементов интерфейса
        set(newValue) {
            field = newValue
            loadBustCounts() // Загружаем значения при установке новых данных
            notifyDataSetChanged()
        }

    private fun loadBustCounts() { // метод для загрузки значений
        coroutineScope.launch{
            data.forEachIndexed{ index, bust ->
                val key = intPreferencesKey("bust_count_$index")
                val loadedCount = context.dataStore.data.map { preferences ->
                    preferences[key] ?: 0
                } .first()
                    bust.count = loadedCount
            }
            withContext(Dispatchers.Main) {
                notifyDataSetChanged() // Обновляем адаптер после загрузки данных
            }
        }
    }

    private fun saveBustCounts () { // метод для загрузки значений
        coroutineScope.launch {
            data.forEachIndexed { index, bust ->
                val key = intPreferencesKey("bust_count_$index")
                context.dataStore.edit{ preferences ->
                    preferences[key] = bust.count // сохраняем текущее значение
                }
            }
        }
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
                    saveBustCounts() // Сохраняем обновленные значения
                }
            }

        }

    }

    class BustViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { // хранение ссылок на view
        val bustImage: ImageView= itemView.findViewById(R.id.bustImage)
        val bustName: TextView = itemView.findViewById(R.id.bustName)
        val bustLvl: Button = itemView.findViewById(R.id.bustButton)
        val bustPrice: TextView = itemView.findViewById(R.id.bustPrice)
    }
}