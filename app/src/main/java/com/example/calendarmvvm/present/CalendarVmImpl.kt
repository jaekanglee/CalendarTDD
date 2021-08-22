package com.example.calendarmvvm.present

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calendarmvvm.model.CalendarModel
import com.example.calendarmvvm.model.DayListEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface ICalendarVm{

    fun onClickBeforeMonthBtn()
    fun onClickNextMonthBtn()
    fun onClickYearBtn()
    fun onClickMonthBtn()

}

@HiltViewModel
class CalendarVmImpl @Inject constructor(
    private val model : CalendarModel
) : ViewModel() {


    private val _titleYear = MutableLiveData(0)
    val titleYear  : LiveData<Int>
    get() = _titleYear

    private val _titleMonth = MutableLiveData(0)
    val titleMonth : LiveData<Int>
    get() = _titleMonth

    private val _dayList = MutableLiveData(ArrayList<DayListEntity>())
    val dayList : LiveData<List<DayListEntity>>
    get() = _dayList.value.run {
        MutableLiveData(this as List<DayListEntity>)
    }



}