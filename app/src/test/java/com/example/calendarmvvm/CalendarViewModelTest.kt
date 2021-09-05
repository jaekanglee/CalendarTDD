package com.example.calendarmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
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
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Config(sdk = [29], application = HiltTestApplication::class)
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
class CalendarViewModelTest {

//   @Module
//   @InstallIn(SingletonComponent::class)
//   abstract class  TestModule {
//
//      @Singleton
//      @Binds
//      abstract fun bindCalendarModel(model : CalendarModelImpl): CalendarModel
//
//   }

    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var model: CalendarModel

    lateinit var viewmodel: CalendarVmImpl

    @Before
    fun setUp() {
        hiltRule.inject()

        viewmodel = CalendarVmImpl(model)
    }

    @Test
    fun `onClickBeforeMonthBtn는 현재 달 + 1을 정확히 반환한다`() {

        val currentMonth = Calendar.getInstance().run {
            get(Calendar.MONTH)+1
        }
        val observable = viewmodel.titleMonth.testObserver()

        viewmodel.onClickBeforeMonthBtn()
        Assert.assertEquals(observable.observedValues.value?.peekContent(), currentMonth-1)

    }

    @Test
    fun `onClickNextMonthBtn는 현재 달 +1을 정확히 반환한`(){
        val currentMonth = Calendar.getInstance().run {
            get(Calendar.MONTH)+1
        }
        val observable = viewmodel.titleMonth.testObserver()

        viewmodel.onClickNextMonthBtn()
        Assert.assertEquals(observable.observedValues.value?.peekContent(), currentMonth+1)
    }

    @Test
    fun `onClickYearBtn()은 1930 ~ 2025년까지 리스트를 반환한다`(){
        var isCorrect =false
        for(i in 0 .. 100){
            viewmodel.initCalendarModel()
            viewmodel.onClickYearBtn()
            val yearList=viewmodel.yearList.value?.peekContent()
            if(yearList.isNullOrEmpty()){
                Assert.fail()
            }
            else{
                val firstValue = yearList[0] =="1930"
                val lastValue = yearList[yearList.size-1] =="2025"
                isCorrect = firstValue && lastValue
                println("${firstValue}/${lastValue} / ${isCorrect}")
                if(!isCorrect)
                    break
            }
        }
        Assert.assertEquals(isCorrect,true)
    }

    @Test
    fun `onCLickMonthBtn()은 1 ~12월 리스트를 반환한다`(){
        var isCorrect =false
        for(i in 0 .. 100){
            viewmodel.initCalendarModel()
            viewmodel.onClickMonthBtn()
            val monthList=viewmodel.monthList.value?.peekContent()
            if(monthList.isNullOrEmpty()){
                Assert.fail()
            }
            else{
                val firstValue = monthList[0] =="1"
                val lastValue = monthList[monthList.size-1] =="12"
                isCorrect = firstValue && lastValue
                println("${firstValue}/${lastValue} / ${isCorrect}")
                if(!isCorrect)
                    break
            }
        }
        Assert.assertEquals(isCorrect,true)
    }


    @Test
    fun `getDayListOnCurrentMonth()는 현재 달의 일 리스트를 모두 반환한다`(){
        viewmodel.initCalendarModel()
        viewmodel.getDayListOnCurrentMonth()
        val result =viewmodel.dayList.value?.peekContent()
        if(result.isNullOrEmpty())
            Assert.fail()

        println("----------------\n${result}\n-------------------")
        Assert.assertEquals(result!=null,true)
    }
}