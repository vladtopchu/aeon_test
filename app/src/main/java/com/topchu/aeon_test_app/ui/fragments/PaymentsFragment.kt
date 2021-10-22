package com.topchu.aeon_test_app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.topchu.aeon_test_app.R
import com.topchu.aeon_test_app.adapters.BasicAdapter
import com.topchu.aeon_test_app.databinding.FragmentPaymentsBinding
import com.topchu.aeon_test_app.ui.viewmodels.PaymentsViewModel
import com.topchu.aeon_test_app.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PaymentsFragment: Fragment() {

    private var _binding: FragmentPaymentsBinding? = null
    private val binding get() = _binding!!

    val viewModel: PaymentsViewModel by viewModels()
    private lateinit var recyclerViewAdapter: BasicAdapter

    @Inject
    lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val decoration = DividerItemDecoration(
            requireContext().applicationContext, DividerItemDecoration.VERTICAL)
        binding.recyclerView.addItemDecoration(decoration)
        recyclerViewAdapter = BasicAdapter()
        binding.recyclerView.adapter = recyclerViewAdapter

        viewModel.getPaymentsObserver().observe(viewLifecycleOwner, {
            if (it != null) {
                recyclerViewAdapter.setPayments(it)
                recyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(requireContext(), "Произошла ошибка", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.getPayments(sharedPref.getUserToken()!!)

        binding.logout.setOnClickListener {
            sharedPref.wipeUserToken()
            findNavController().navigate(R.id.authFragment)
        }
    }


    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}