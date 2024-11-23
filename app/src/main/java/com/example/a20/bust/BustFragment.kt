package com.example.a20.bust

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
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

        // Инициализация RecyclerView
        bustRecycler = view.findViewById(R.id.busts)
        bustAdapter = BustAdapter()

        bustRecycler.layoutManager = LinearLayoutManager(requireContext())
        bustRecycler.adapter = bustAdapter

        // Установка данных в адаптер
        bustAdapter.data = BustGeneration.getBusts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding= null
    }
}
