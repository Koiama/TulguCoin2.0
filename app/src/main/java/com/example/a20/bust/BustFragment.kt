package com.example.a20.bust

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a20.R
import com.example.a20.adapter.BustAdapter
import com.example.a20.databinding.FragmentBustBinding

class BustFragment : Fragment(R.layout.fragment_bust) {

    private var binding: FragmentBustBinding?= null


    private lateinit var bustRecycler: RecyclerView
    private lateinit var bustAdapter: BustAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentBustBinding.bind(view)

        // Получите аргументы
        val args: BustFragmentArgs? = arguments?.let { BustFragmentArgs.fromBundle(it) }
        val counter = args?.counter ?: 0 // Получаем значение counter, если оно не передано, используем 0

        // Инициализация RecyclerView
        bustRecycler = view.findViewById(R.id.busts)
        bustAdapter = BustAdapter(counter)

        bustRecycler.layoutManager = LinearLayoutManager(requireContext())
        bustRecycler.adapter = bustAdapter

        // Установка данных в адаптер
        bustAdapter.data = BustGeneration.getBusts()

        binding?.MainButton?.setOnClickListener {
            findNavController().navigate(BustFragmentDirections.actionBustToButton(counter))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
