package com.example.recorderapp

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.commit
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.example.recorderapp.cycling.CyclingFragment
import com.example.recorderapp.databinding.ActivityMainBinding
import com.example.recorderapp.running.RunningFragment
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(),NavigationBarView.OnItemSelectedListener{

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNav.setOnItemSelectedListener (this)
        setSupportActionBar(findViewById(R.id.my_toolbar1))



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val returnstatement =  when(item.itemId){
            R.id.reset_running -> {
                showconfirmationdialog("running")
                true
            }

            R.id.reset_cycling ->{
                showconfirmationdialog("cycling")
                true
            }

            R.id.reset_all ->{
                showconfirmationdialog("all")
                true
            }

            else -> super.onOptionsItemSelected(item)
        }



        return returnstatement
    }

    private fun showconfirmationdialog(selection: String) {
        AlertDialog.Builder(this)
            .setTitle("Reset $selection records")
            .setMessage("Are you sure you want to clear the records?")
            .setPositiveButton("Yes"){_,_->
                when(selection){
                    "all" -> {
                        getSharedPreferences("running", Context.MODE_PRIVATE).edit { clear() }
                        getSharedPreferences("cycling",Context.MODE_PRIVATE).edit { clear() }
                    }
                    else -> {getSharedPreferences(selection, Context.MODE_PRIVATE).edit { clear() }}
                }
                when(binding.bottomNav.selectedItemId){
                    R.id.nav_cycling -> OnclickCycling()
                    R.id.nav_running -> OnClickRunning()
                    else -> {}
                }
                val snackbar = Snackbar.make(binding.root,"Record Cleared Successfully!",Snackbar.LENGTH_LONG)
                snackbar.anchorView = binding.bottomNav
                snackbar.setAction("Undo"){
                    //some code to restore the records
                }
                snackbar.show()


            }
            .setNegativeButton("No",null)
            .show()
    }


    private fun OnclickCycling() {
        supportFragmentManager.commit {
            replace(R.id.frame_layout, CyclingFragment())
        }
    }

    private fun OnClickRunning() {
        supportFragmentManager.commit {
            replace(R.id.frame_layout, RunningFragment())
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.nav_cycling -> {
                OnclickCycling()
                true
            }

            R.id.nav_running -> {
                OnClickRunning()
                true
            }

            else -> false
        }

//        if (item.itemId == R.id.nav_cycling){
//            OnclickCycling()
//            return true
//        }else if (item.itemId == R.id.nav_running){
//            OnClickRunning()
//            return true
//        }else{
//            return false
//        }

    }
}