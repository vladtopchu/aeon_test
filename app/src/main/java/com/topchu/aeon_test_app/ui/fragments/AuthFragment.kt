package com.topchu.aeon_test_app.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.topchu.aeon_test_app.R
import com.topchu.aeon_test_app.data.remote.models.LoginModel
import com.topchu.aeon_test_app.databinding.FragmentAuthBinding
import com.topchu.aeon_test_app.ui.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment: Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.success.observe(viewLifecycleOwner, {
            if(it){
                findNavController().navigate(R.id.paymentsFragment)
            }
        })

        viewModel.error.observe(viewLifecycleOwner, {
            if(it){
                Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_LONG).show()
            }
        })

        binding.processLogin.setOnClickListener {
            if(binding.login.text.toString().isEmpty() ||
                binding.password.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "Заполните все поля!", Toast.LENGTH_LONG).show()
            } else {
                viewModel.login(LoginModel(binding.login.text.toString(), binding.password.text.toString()))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}