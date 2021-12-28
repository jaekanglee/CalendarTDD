package com.example.calendarmvvm.present

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calendarmvvm.Event
import com.example.calendarmvvm.model.DayListEntity
import com.example.calendarmvvm.model.ICalendarModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


interface AdapterListener {
    fun getItemsSize(): Int
    fun getItemFromIndex(index: Int): DayListEntity?
}

@HiltViewModel
class CalendarVmImpl @Inject constructor(
    private val model: ICalendarModel
) : ViewModel(), AdapterListener {

    private val compositeDisposable = CompositeDisposable()

    private val _calendarTitle = MutableLiveData<String>()
    val calendarTitle: LiveData<String>
        get() = _calendarTitle

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

    private val _actionSelectYearMonth = MutableLiveData<Event<Unit>>()
    val actionSelectYearMonth : LiveData<Event<Unit>>
    get() = _actionSelectYearMonth

    init {
        initCalendarModel()
    }

    fun initCalendarModel() {
        Single.fromCallable {
            model.initCalendarInstance().run {
                _calendarTitle.postValue("${model.getYear()}년 ${model.getMonth()}월")
            }
        }
            .map {
                getDayListOnCurrentMonth()
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    _dayList.value = Event(it)
                },
                onError = {
                    _dayList.value = Event(ArrayList())
                }
            ).addTo(compositeDisposable)

    }

    fun getDayListOnCurrentMonth(): List<DayListEntity> {
        return ArrayList<DayListEntity>().apply {
            addAll(model.createWeeksOfDays())
            addAll(model.getDayList())
        }
    }

    fun onClickBeforeMonthBtn() {
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


    fun onClickNextMonthBtn() {
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

    fun onClickYearBtn() {
        _yearList.value = Event(model.getYearList())
    }

    fun onClickMonthBtn() {
        _monthList.value = Event(model.getMonthList())
    }

    override fun getItemsSize(): Int {
        return dayList.value?.peekContent()?.size ?: 0
    }

    override fun getItemFromIndex(index: Int): DayListEntity? {
        return dayList.value?.peekContent()?.get(index)
    }


    fun onActionSelectYearMonth(){
        _actionSelectYearMonth.value= Event(Unit)
    }

}