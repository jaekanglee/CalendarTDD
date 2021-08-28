package com.example.calendarmvvm

import com.example.calendarmvvm.di.ModelModule
import com.example.calendarmvvm.model.CalendarModel
import com.example.calendarmvvm.model.CalendarModelImpl
import com.example.calendarmvvm.present.CalendarVmImpl
import com.example.calendarmvvm.present.MainActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import javax.inject.Singleton

@UninstallModules(ModelModule::class)
@Config(sdk=[29],application = HiltTestApplication::class)
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
 class CalendarViewModelTest {

   @Module
   @InstallIn(SingletonComponent::class)
   abstract class  TestModule {

      @Singleton
      @Binds
      abstract fun bindCalendarModel(model : CalendarModelImpl): CalendarModel

   }



    @get:Rule
    var hiltRule = HiltAndroidRule(this)

     @Inject lateinit var  model : CalendarModel

     lateinit var viewmodel :CalendarVmImpl
    @Before
    fun setUp(){
        hiltRule.inject()

        viewmodel = CalendarVmImpl(model)
    }

    @Test
    fun `sdfsdf`(){
        viewmodel.onClickBeforeMonthBtn()
        Assert.assertEquals(viewmodel.titleYear.value,99)
    }
}