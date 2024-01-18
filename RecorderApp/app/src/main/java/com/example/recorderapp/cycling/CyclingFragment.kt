package com.example.recorderapp.cycling

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recorderapp.databinding.FragmentCyclingBinding
import com.example.recorderapp.editRecord.EditRecordActivity

class CyclingFragment: Fragment() {
    private lateinit var binding:FragmentCyclingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCyclingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
    }

    override fun onResume() {
        super.onResume()
        DisplayRecords()
    }

    private fun DisplayRecords() {
        val cyclingPreferences = requireContext().getSharedPreferences("cycling", Context.MODE_PRIVATE)
        binding.textViewLongestRideValue.text = cyclingPreferences.getString("Longest Ride record",null)
        binding.textViewLongestRideDate.text = cyclingPreferences.getString("Longest Ride date",null)
        binding.textViewBiggestClimbValue.text = cyclingPreferences.getString("Biggest Climb record",null)
        binding.textViewBiggestClimbDate.text = cyclingPreferences.getString("Biggest Climb date",null)
        binding.textViewBestAverageSpeedValue.text = cyclingPreferences.getString("Best Average Speed record",null)
        binding.textViewBestAverageSpeedDate.text = cyclingPreferences.getString("Best Average Speed date",null)
    }

    private fun setupClickListener() {
        binding.containerLongest.setOnClickListener { launchcyclingrecord("Longest Ride","Distance") }
        binding.containerBiggest.setOnClickListener { launchcyclingrecord("Biggest Climb","Height") }
        binding.containerBest.setOnClickListener { launchcyclingrecord("Best Average Speed","Average Speed") }
    }

    private fun launchcyclingrecord(record : String,recordfieldhint:String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("screen_data",EditRecordActivity.Screendata(record,"cycling",recordfieldhint))
        startActivity(intent)
    }
}