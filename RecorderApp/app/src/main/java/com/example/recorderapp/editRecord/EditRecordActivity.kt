package com.example.recorderapp.editRecord

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.edit
import com.example.recorderapp.R
import com.example.recorderapp.databinding.ActivityEditRecordBinding
import java.io.Serializable

class EditRecordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditRecordBinding
    private val screendata: Screendata by lazy {
         intent.getSerializableExtra("screen_data") as Screendata
    }
    private val runningPreferences by lazy { getSharedPreferences(screendata.sharedPreferencesName,Context.MODE_PRIVATE) }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        var actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
        title = "${screendata.record} Record"
        binding.textInputRecord.hint = screendata.recordFieldHint
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        binding.buttonClearRecord.setOnClickListener {
            clearRecord()
            finish()
        }
        displayRecord()
    }

    private fun clearRecord() {
        runningPreferences.edit {
            remove("${screendata.record} record")
            remove("${screendata.record} date")
        }
    }

    private fun displayRecord() {
        binding.editTextRecord.setText(runningPreferences.getString("${screendata.record} record",null))
        binding.editTextDate.setText(runningPreferences.getString("${screendata.record} date",null))
    }


    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        val runningPreferences = getSharedPreferences(screendata.sharedPreferencesName,Context.MODE_PRIVATE)
        runningPreferences.edit {
            putString("${screendata.record} record",record)
            putString("${screendata.record} date",date)
        }
    }
    data class Screendata(
        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ): Serializable
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}