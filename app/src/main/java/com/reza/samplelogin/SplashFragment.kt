package com.reza.samplelogin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.reza.samplelogin.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay

class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          moveToLoginFragment()
    }

    private fun moveToLoginFragment() {
        lifecycle.coroutineScope.launchWhenCreated {
            delay(5000)
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}