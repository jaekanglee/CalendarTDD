package com.example.calendarmvvm.model

import android.util.Log
import dagger.hilt.android.scopes.ViewModelScoped
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

interface CalendarModel {
    fun initCalendarInstance()

    fun getYear(): Int
    fun getMonth(): Int
    fun getDay(): Int
    fun clickToBeforeMonth(): Int
    fun clickToNextMonth(): Int
    fun getCurrentDate(): String

    fun getDetectFirstDayOfWeek(): Int

    fun isToday(today: String): Boolean
    fun isSunday(today: String): Boolean

    fun isSaturday(today: String): Boolean


    fun setMonth(month: Int)
    fun setYear(year: Int)
    fun setDay(day: Int)

    fun getDayList(): List<DayListEntity>

    fun createBlankDayToList(): List<DayListEntity>
}


class CalendarModelImpl @Inject constructor(): CalendarModel {
    var cInstance: Calendar? = null

    override fun initCalendarInstance() {
        cInstance = Calendar.getInstance()
    }

    override fun getYear(): Int {
        cInstance ?: throw NullPointerException("Calendar Instance is Null")
        return cInstance?.get(Calendar.YEAR) ?: 0
    }

    override fun getMonth(): Int {
        cInstance ?: throw NullPointerException("Calendar Instance is Null")
        return cInstance?.get(Calendar.MONTH)?.let {
            it + 1
        } ?: kotlin.run {
            0
        }
    }

    override fun getDay(): Int {
        cInstance ?: throw NullPointerException("Calendar Instance is Null")
        return cInstance?.get(Calendar.DAY_OF_MONTH) ?: 0
    }

    override fun clickToBeforeMonth(): Int {
        cInstance ?: throw NullPointerException("Calendar Instance is Null")

        val currentMonth = cInstance?.get(Calendar.MONTH)
        currentMonth ?: throw NullPointerException("current Month Instance is Null")

        if (currentMonth == 0) {
            cInstance?.set(Calendar.MONTH, 11)
        } else {
            cInstance?.set(Calendar.MONTH, currentMonth - 1)
        }
        return getMonth()
    }


    override fun clickToNextMonth(): Int {
        cInstance ?: throw NullPointerException("Calendar Instance is Null")
        val currentMonth = cInstance?.get(Calendar.MONTH)
        currentMonth ?: throw NullPointerException("current Month Instance is Null")
        cInstance?.set(Calendar.MONTH, currentMonth + 1)
        return cInstance?.get(Calendar.MONTH) ?: 0
    }

    override fun getCurrentDate(): String {
        cInstance ?: throw NullPointerException("Calendar Instance is Null")
        return cInstance?.run {
            "${getYear()}.${getMonth()}.${getDay()}"
        } ?: kotlin.run {
            throw NullPointerException("Not fount Calendar Data")
        }
    }

    override fun isToday(today: String): Boolean {
        Calendar.getInstance().run {
            var currentYear: Int = get(Calendar.YEAR)
            var currentMonth: Int = get(Calendar.MONTH) + 1
            var currentDay: Int = get(Calendar.DAY_OF_MONTH)
            try {
                today.split(".").also {
                    return currentYear == it[0].toInt() &&
                            currentMonth == it[1].toInt() &&
                            currentDay == it[2].toInt()
                }
            } catch (e: Exception) {
                throw Exception("Date Convert Exception!")
            }
        }
    }


    override fun isSunday(today: String): Boolean {
        Calendar.getInstance().run {
            try {
                today.split(".").also {
                    set(Calendar.YEAR, it[0].toInt())
                    set(Calendar.MONTH, it[1].toInt() - 1)
                    set(Calendar.DAY_OF_MONTH, it[2].toInt())
                    return get(Calendar.DAY_OF_WEEK) == 1
                }
            } catch (e: Exception) {
                throw Exception("Date Convert Exception!")
            }
        }
    }

    override fun isSaturday(today: String): Boolean {
        Calendar.getInstance().run {
            try {
                today.split(".").also {
                    set(Calendar.YEAR, it[0].toInt())
                    set(Calendar.MONTH, it[1].toInt() - 1)
                    set(Calendar.DAY_OF_MONTH, it[2].toInt())
                    return get(Calendar.DAY_OF_WEEK) == 7
                }
            } catch (e: Exception) {
                throw Exception("Date Convert Exception!")
            }
        }
    }

    override fun getDetectFirstDayOfWeek(): Int {
        return cInstance?.let {
            it.get(Calendar.DAY_OF_WEEK)
        } ?: throw NullPointerException("Calendar Instance is Null")

    }

    override fun setMonth(month: Int) {
        cInstance?.set(Calendar.MONTH, month - 1)
            ?: throw NullPointerException("Calendar Instance is Null")

    }

    override fun setYear(year: Int) {
        cInstance?.set(Calendar.YEAR, year)
            ?: throw NullPointerException("Calendar Instance is Null")
    }

    override fun setDay(day: Int) {
        cInstance?.set(Calendar.DAY_OF_MONTH, day)
            ?: throw NullPointerException("Calendar Instance is Null")
    }

    override fun getDayList(): List<DayListEntity> {
        cInstance ?: throw NullPointerException("Calendar Instance is Null")
        val lastDayOfMonth = cInstance?.getActualMaximum(Calendar.DATE)

        val result = ArrayList<DayListEntity>()

        createBlankDayToList().run {
            result.addAll(this)
        }
        return lastDayOfMonth?.let {lastDay ->

            for(i in 1 .. lastDay){
                DayListEntity(DayListType.DAY, i).run{
                    result.add(this)
                }
            }
            result
        } ?: kotlin.run {
            throw NullPointerException("LastDayOnfMonth Instance is Null")
        }
    }

    override fun createBlankDayToList(): List<DayListEntity> {
        cInstance ?: throw NullPointerException("Calendar Instance is Null")
        val firstDay = getDetectFirstDayOfWeek()
        val result = ArrayList<DayListEntity>()
        for (i in 1 until firstDay) {
            DayListEntity(
                DayListType.BLANK,
                0
            ).run {
                result.add(this)
            }
        }
        return result
    }

}