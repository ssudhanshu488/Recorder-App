package com.example.recorderapp.running

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recorderapp.databinding.FragmentRunningBinding
import com.example.recorderapp.editRecord.EditRecordActivity

class RunningFragment: Fragment() {
    private lateinit var binding: FragmentRunningBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRunningBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListener()
    }

    override fun onResume() {
        super.onResume()
        DisplayRecord()
    }

    private fun DisplayRecord() {
        val runningPrefences = requireContext().getSharedPreferences("running",Context.MODE_PRIVATE)
        binding.textView5kmValue.text = runningPrefences.getString("5km record",null)
        binding.textView5kmDate.text = runningPrefences.getString("5km date", null)
        binding.textView10kmValue.text = runningPrefences.getString("10km record", null)
        binding.textView10kmDate.text = runningPrefences.getString("10km date", null)
        binding.textViewHalfMarathonValue.text = runningPrefences.getString("Half Marathon record", null)
        binding.textViewHalfMarathonDate.text = runningPrefences.getString("Half Marathon date", null)
        binding.textViewFullMarathonValue.text = runningPrefences.getString("Marathon record", null)
        binding.textViewMarathonDate.text = runningPrefences.getString("Marathon date", null)
    }

    private fun setupClickListener() {
        binding.container5km.setOnClickListener { launchRunningRecord("5km") }
        binding.container10km.setOnClickListener { launchRunningRecord("10km") }
        binding.containerHalfMarathon.setOnClickListener { launchRunningRecord("Half Marathon") }
        binding.containerMarathon.setOnClickListener { launchRunningRecord("Marathon") }
    }

    private fun launchRunningRecord(distance: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("screen_data",EditRecordActivity.Screendata(distance,"running","time" ))
        startActivity(intent)
    }
}