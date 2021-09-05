package com.example.calendarmvvm.present

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calendarmvvm.Event
import com.example.calendarmvvm.model.CalendarModel
import com.example.calendarmvvm.model.DayListEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface ICalendarVm {

    fun onClickBeforeMonthBtn()
    fun onClickNextMonthBtn()
    fun onClickYearBtn()
    fun onClickMonthBtn()
    fun initCalendarModel()
    fun getDayListOnCurrentMonth()

}

interface AdapterListener{
    fun getItemsSize():Int
    fun getItemFromIndex(index:Int):DayListEntity?
}

@HiltViewModel
class CalendarVmImpl @Inject constructor(
    private val model: CalendarModel
) : ViewModel(), ICalendarVm ,AdapterListener{

    private val compositeDisposable = CompositeDisposable()

    private val _titleYear = MutableLiveData<Event<Int>>()
    val titleYear: LiveData<Event<Int>>
        get() = _titleYear

    private val _titleMonth = MutableLiveData<Event<Int>>()
    val titleMonth: LiveData<Event<Int>>
        get() = _titleMonth

    private val _yearList = MutableLiveData<Event<List<String>>>()
    val yearList: LiveData<Event<List<String>>>
        get() = _yearList


    private val _monthList = MutableLiveData<Event<List<String>>>()
    val monthList: LiveData<Event<List<String>>>
        get() = _monthList

    private val _dayList = MutableLiveData<Event<List<DayListEntity>>>()
    val dayList: LiveData<Event<List<DayListEntity>>>
        get() = _dayList

    init {
        initCalendarModel()
    }

    override fun initCalendarModel() {
        Completable.fromAction {
            model.initCalendarInstance()
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                getDayListOnCurrentMonth()
            }.addTo(compositeDisposable)

    }

    override fun getDayListOnCurrentMonth() {
        _dayList.value=Event(model.getDayList())
    }


    override fun onClickBeforeMonthBtn() {
        Single.fromCallable {
            model.beforeMonth()
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _titleMonth.value = Event(model.getMonth())
                },
                onError = {
                    _titleMonth.value = Event(model.getMonth())
                }
            ).addTo(compositeDisposable)


    }

    override fun onClickNextMonthBtn() {
        Single.fromCallable {
            model.nextMonth()
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _titleMonth.value = Event(model.getMonth())
                },
                onError = {
                    _titleMonth.value = Event(model.getMonth())
                }
            ).addTo(compositeDisposable)

    }

    override fun onClickYearBtn() {
        _yearList.value = Event(model.getYearList())
    }

    override fun onClickMonthBtn() {
        _monthList.value = Event(model.getMonthList())
    }

    override fun getItemsSize(): Int {
        return dayList.value?.peekContent()?.size?:0
    }

    override fun getItemFromIndex(index: Int): DayListEntity? {
        return dayList.value?.peekContent()?.get(index)
    }


}