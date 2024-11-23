package com.example.a20

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.a20.databinding.FragmentSecondBinding
import androidx.navigation.fragment.navArgs

class SecondFragment : Fragment(R.layout.fragment_second){

    private val args: SecondFragmentArgs by navArgs()
    private var binding: FragmentSecondBinding?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSecondBinding.bind(view)

        binding?.content?.text=args.data
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}