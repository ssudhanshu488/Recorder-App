package com.example.recorderapp.cycling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.recorderapp.R
import com.example.recorderapp.databinding.ActivityEditCyclingBinding

class EditCyclingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditCyclingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditCyclingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val distance2 = intent.getStringExtra("Distance2")
        title = "$distance2 Record"
        setSupportActionBar(findViewById(R.id.my_toolbar2))
        var actionBar = getSupportActionBar()
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }
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