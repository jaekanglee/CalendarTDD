package com.example.calendarmvvm.present

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calendarmvvm.R
import com.example.calendarmvvm.model.CalendarModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}