package com.example.calendarmvvm.present

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calendarmvvm.R
import com.example.calendarmvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewmodel: CalendarVmImpl by viewModels()

    val adapter: CalendarAdapter by lazy {
        CalendarAdapter(viewmodel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.adapter = adapter
        binding.viewmodel = viewmodel

        setObserver()
    }


    fun setObserver() {

        with(viewmodel) {
            dayList.observe(this@MainActivity) {
                it.peekContent().let {
                    adapter.notifyDataSetChanged()
                }
            }


            actionSelectYearMonth.observe(this@MainActivity) {
                it.getContentIfNotHandled()?.let {
                    Log.e("Shot Sheet", "YEar and Month ")
                }
            }
        }

    }
}