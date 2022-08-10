package com.example.shemajamebeli_5.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.shemajamebeli_5.databinding.ActivityMainBinding
import com.example.shemajamebeli_5.viewmodel.ViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fieldsAdapter by lazy { FieldsAdapter() }
    private val viewModel: ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.rvFields.adapter = fieldsAdapter
        data()
    }

    private fun data() {
        lifecycleScope.launch {
            viewModel.fields()
            viewModel.likusFlow.collect {
                fieldsAdapter.submitList(it)
            }
        }
    }
}