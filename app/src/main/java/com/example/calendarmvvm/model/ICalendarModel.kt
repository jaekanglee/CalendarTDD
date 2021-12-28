package com.example.calendarmvvm.model

interface ICalendarModel {
    fun initCalendarInstance()
    fun getYear(): Int
    fun getMonth(): Int
    fun getDay(): Int
    fun beforeMonth(): Int
    fun nextMonth(): Int
    fun getCurrentDate(): String
    fun createYearList()
    fun createMonthlist()
    fun getDetectFirstDayOfWeek(): Int
    fun isToday(today: String): Boolean
    fun isSunday(today: String): Boolean
    fun isSaturday(today: String): Boolean
    fun setMonth(month: Int)
    fun setYear(year: Int)
    fun setDay(day: Int)
    fun getDayList(): List<DayListEntity>
    fun createBlankDayToList(): List<DayListEntity>
    fun getYearList(): List<String>
    fun getMonthList(): List<String>
    fun createWeeksOfDays() : List<DayListEntity>
}