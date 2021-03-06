package com.topchu.aeon_test_app.presentation.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.topchu.aeon_test_app.R
import com.topchu.aeon_test_app.data.remote.models.AuthModel
import com.topchu.aeon_test_app.databinding.FragmentAuthBinding
import com.topchu.aeon_test_app.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment: Fragment() {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    @Inject
    lateinit var sharedPref: SharedPref

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

        viewModel.state.observe(viewLifecycleOwner, {
            if(it.isLoading) {
                binding.progressCircular.visibility = View.VISIBLE
            } else if(!it.isLoading && binding.progressCircular.visibility == View.VISIBLE) {
                binding.progressCircular.visibility = View.GONE
            }
            if(it.error.isNotBlank()){
                Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
            }
            if(it.token != null){
                sharedPref.setUserToken(it.token)
                findNavController().navigate(R.id.paymentsFragment)
            }
        })

        binding.processLogin.setOnClickListener {
            if(binding.login.text.toString().isEmpty() ||
                binding.password.text.toString().isEmpty()){
                Toast.makeText(requireContext(), "?????????????????? ?????? ????????!", Toast.LENGTH_LONG).show()
            } else {
                viewModel.login(AuthModel(binding.login.text.toString(), binding.password.text.toString()))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}