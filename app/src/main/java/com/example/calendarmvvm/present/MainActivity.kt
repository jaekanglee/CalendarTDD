package com.example.calendarmvvm.present

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.calendarmvvm.R
import com.example.calendarmvvm.databinding.ActivityMainBinding
import com.example.calendarmvvm.model.CalendarModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    val viewmodel : CalendarVmImpl by viewModels()

    val adapter : CalendarAdapter by lazy {
        CalendarAdapter(viewmodel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner=this
        binding.adapter=adapter


        viewmodel.dayList.observe(this){
            it.peekContent()?.let {
                adapter.notifyDataSetChanged()
            }
        }
    }
}