package com.example.a20

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.a20.databinding.FragmentFirstBinding

class FirstFragment: Fragment(R.layout.fragment_first){

    private var binding:FragmentFirstBinding?=null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentFirstBinding.bind(view)

        binding?.navigateButton?.setOnClickListener{
            val data = binding?.field?.text.toString()
            findNavController().navigate(FirstFragmentDirections.actionFirstToSecond(data))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}
