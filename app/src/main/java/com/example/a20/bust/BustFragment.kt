package com.example.a20.bust

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a20.R
import com.example.a20.adapter.BustAdapter
import com.example.a20.databinding.FragmentBustBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "bust_data")

class BustFragment : Fragment(R.layout.fragment_bust) {

    private var binding: FragmentBustBinding? = null
    private lateinit var bustRecycler: RecyclerView
    private lateinit var bustAdapter: BustAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBustBinding.bind(view)

        // Получите аргументы
        val args: BustFragmentArgs? = arguments?.let { BustFragmentArgs.fromBundle(it) }
        val counter = args?.counter ?: 0 // Получаем значение counter, если оно не передано, используем 0

        // Инициализация RecyclerView
        bustRecycler = view.findViewById(R.id.busts)

        // Изменение: передаем dataStore в адаптер
        bustAdapter = BustAdapter(requireContext(), viewLifecycleOwner.lifecycleScope, counter, requireContext().dataStore)

        bustRecycler.layoutManager = LinearLayoutManager(requireContext())
        bustRecycler.adapter = bustAdapter

        // Загрузка данных из DataStore
        loadBustCounts()

        binding?.MainButton?.setOnClickListener {
            findNavController().navigate(BustFragmentDirections.actionBustToButton(counter))
        }
    }

    private fun loadBustCounts() {
        lifecycleScope.launch {
            val loadedCounts = mutableListOf<Int>()
            val busts = BustGeneration.getBusts() // Получаем базовые данные
            busts.forEachIndexed { index, bust ->
                val key = intPreferencesKey("bust_count_$index")
                val loadedCount = requireContext().dataStore.data.map { preferences ->
                    preferences[key] ?: 0
                }.first()
                loadedCounts.add(loadedCount)
            }

            // Обновляем значения в busts
            loadedCounts.forEachIndexed { index, count ->
                busts[index].count = count
            }

            // Устанавливаем данные в адаптер
            bustAdapter.data = busts
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
