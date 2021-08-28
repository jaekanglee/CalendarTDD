package com.example.calendarmvvm

import com.example.calendarmvvm.di.ModelModule
import com.example.calendarmvvm.model.CalendarModel
import com.example.calendarmvvm.present.CalendarVmImpl
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
 class CalendarViewModelTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @Inject
    lateinit var viewmodel : CalendarVmImpl

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun `sdfsdf`(){
//        viewModel.onClickBeforeMonthBtn()
//        Assert.assertEquals(viewModel.titleYear.value,99)
    }
}