package com.example.calendarmvvm

import com.example.calendarmvvm.model.CalendarModel
import com.example.calendarmvvm.model.CalendarModelImpl
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*


@RunWith(JUnit4::class)
class CalendarTest {
// 모델 생성
    // 날짜 얻어오기
    // 날짜 년 월 일 확인
    // 년 월 일 교체
    // 이전 달
    // 이후 달
    // 오늘이 맞는지
    // 해당 일이 토요일인지
    // 해당 일이 일요일인지
    // 전체 년월일 반환

    lateinit var calendarModelImpl: CalendarModelImpl

    @Before
    fun init() {
        calendarModelImpl = CalendarModelImpl()
        calendarModelImpl.initCalendarInstance()
    }

    @Test
    fun initCalendarInstance() {
        Assert.assertNotNull(calendarModelImpl.cInstance)
    }
    @Test
    fun `1_initCalendarInstance()를 호출하면 cInstance는 Null이 아니다`(){
        calendarModelImpl.initCalendarInstance()
        Assert.assertNotNull(calendarModelImpl.cInstance)
    }

    @Test
    fun `2_setYear()는 입력받은 년을 설정한다`() {
        calendarModelImpl.setYear(2020)
        Assert.assertEquals(2020, calendarModelImpl.getYear())
    }

    @Test
    fun `3_setMonth()는 입력받은 월을 설정한다`() {
        calendarModelImpl.setMonth(1)
        Assert.assertEquals(1, calendarModelImpl.getMonth())
    }

    @Test
    fun `4_setDay()는 입력받은 일을 설정한다`() {
        calendarModelImpl.setDay(31)
        Assert.assertEquals(31, calendarModelImpl.getDay())
    }

    //테스트 케이스의 SRP를 위반
//
//    @Test
//    fun `5_setYear(),setMonth(),setDay()는 Input값에 정확한 OutPut을 반환한다`() {
//        calendarModelImpl.run {
//            setYear(2020)
//            setMonth(12)
//            setDay(1)
//        }
//        Assert.assertEquals("2020.12.1", calendarModelImpl.getCurrentDate())
//    }


    @Test
    fun `5_getMonth()는 1 ~ 12사이의 값을 반환한다`() {
        val month = calendarModelImpl.getMonth()
        Assert.assertTrue(month in 1..12)
    }


    @Test
    fun `6_getDay()는 1 ~ 31사이 값을 반환한다`() {
        val day = calendarModelImpl.getDay()
        println("day:$day")
        Assert.assertTrue(day in 1..31)
    }

    @Test
    fun `7_clickToBeforeMonth()는 1~12사이의 값을 반환한다`() {
        val month = calendarModelImpl.clickToBeforeMonth()
        Assert.assertTrue(month in 1..12)
    }

    @Test
    fun `8_clickToBeforeMonth()는 1월 이전 0월이 아닌 12월로 변경된다`() {
        calendarModelImpl.cInstance?.set(Calendar.MONTH, 0)
        val month = calendarModelImpl.clickToBeforeMonth()
        Assert.assertEquals(month, 12)
    }


    @Test
    fun `9_clickToBeforeMonth()는 현재 달 -1값과 동일하다 `() {
        val currentMonth = calendarModelImpl.cInstance?.get(Calendar.MONTH) ?: 0
        val beforeMonth = calendarModelImpl.run {
            clickToBeforeMonth()
            cInstance?.get(Calendar.MONTH)
        }
        println("beforeMonth : $beforeMonth")
        Assert.assertEquals(currentMonth - 1, beforeMonth)
    }

    @Test
    fun `10_clickToNextMonth()는 1 ~ 12 사이의 값을 반환한다`() {
        val month = calendarModelImpl.clickToNextMonth()
        Assert.assertTrue(month in 1..12)
    }

    @Test
    fun `11_clickToNextMonth()는 12월 이후에 13월이 아닌 1월로 변경된다`() {
        calendarModelImpl.cInstance?.set(Calendar.MONTH, 12)
        val month = calendarModelImpl.clickToNextMonth()
        println("Next Month :$month")
        Assert.assertTrue(month == 1)
    }

    @Test
    fun `12_clickToNextMonth()는 현재 달 +1값과 동일하다 `() {
        val currentMonth = calendarModelImpl.cInstance?.get(Calendar.MONTH) ?: 0
        val nextMonth = calendarModelImpl.run {
            clickToNextMonth()
            cInstance?.get(Calendar.MONTH)
        }
        Assert.assertEquals(currentMonth + 1, nextMonth)
    }

    @Test
    fun `13_getCurrentDate()는 현재 년월일을 정확하게 반환한다`() {
        val currentYear = calendarModelImpl.getYear().toString()
        val currentMonth = calendarModelImpl.getMonth().toShort()
        val currentDay = calendarModelImpl.getDay().toString()

        Assert.assertEquals(
            calendarModelImpl.getCurrentDate(),
            "${currentYear}.${currentMonth}.${currentDay}"
        )
    }

    @Test
    fun `14_입력된 날짜가 오늘과 일치하지 않으면 isToday()는 false를 반환한다`() {
        Assert.assertEquals(
            calendarModelImpl.isToday(
                "1993.02.24"
            ), false
        )
    }

    @Test
    fun `15_입력된 날짜가 일요일이면 isSunday()는 True를 반환한다`() {
        Assert.assertEquals(calendarModelImpl.isSunday("2021.08.22"), true)
    }

    @Test
    fun `입력된 날짜가 일요일이 아니면 isSunday()는 false 반환한다`() {
        Assert.assertEquals(calendarModelImpl.isSunday("2021.08.20"), false)
    }


    @Test
    fun `16_입력된 날짜가 토요일이면 isSaturday()는 True를 반환한다`() {
        Assert.assertEquals(calendarModelImpl.isSaturday("2021.08.21"), true)
    }

    @Test
    fun `17_입력된 날짜가 토요일이 아니면 isSaturday()는 false 반환한다`() {
        Assert.assertEquals(calendarModelImpl.isSaturday("2021.08.22"), false)
    }


    @Test
    fun `18_getDetectFirstDayOfWeek()는 2021년8월의 1일 요일을 1로 반환한다`() {
        val firstDayOfWeek = calendarModelImpl.getDetectFirstDayOfWeek()
        Assert.assertEquals(firstDayOfWeek, 1)
    }

    @Test
    fun `19_getDetectFirstDayOfWeek()는 2021년7월의 1일 요일을 5로 반환한다`() {
        calendarModelImpl.run {
            setYear(2021)
            setMonth(7)
            setDay(1)
        }
        val firstDayOfWeek = calendarModelImpl.getDetectFirstDayOfWeek()
        Assert.assertEquals(firstDayOfWeek, 5)
    }

    @Test
    fun `20_getDetectFirstDayOfWeek()는 2020년12월의 1일 요일을 3로 반환한다`() {
        calendarModelImpl.run {
            setYear(2020)
            setMonth(12)
            setDay(1)
        }
        val firstDayOfWeek = calendarModelImpl.getDetectFirstDayOfWeek()
        Assert.assertEquals(firstDayOfWeek, 3)
    }


    @Test
    fun `21_createBlankDayToList()는 현재 월 시작요일 전 빈값값에 대한 블랭크 리스트를 올바르에 ADD한다(카운트)`() {
        val items = calendarModelImpl.createBlankDayToList()
        Assert.assertEquals(items.count(), 0)
    }

    @Test
    fun `22_createBlankDayToList()는 2021년7 시작요일 전 빈값에 대한 블랭크 리스트를 올바르게 ADD한다(카운트)`() {
        calendarModelImpl.cInstance?.set(Calendar.MONTH, 6)
        val items = calendarModelImpl.createBlankDayToList()
        Assert.assertEquals(items.count(), 4)
    }


    @Test
    fun `23_getDayList()는 현재 월 달력의 0번째 셀부터 마지막 날까지의 리스트 갯수를 올바르게 생성한다`() {
        val itemsCount = calendarModelImpl.getDayList().size
        Assert.assertEquals(itemsCount, 31)
    }

    @Test
    fun `24_getDayList()는 2021년7월 달력의 0번째 셀부터 마지막 날까지의 리스트 갯수를 올바르게 생성한다`() {
        calendarModelImpl.setMonth(7)
        val itemsCount = calendarModelImpl.getDayList().size
        Assert.assertEquals(itemsCount, 35)
    }

    @Test
    fun `25_getDayList()의 마지막 요소의 Value는 현재 월의 마지막 Day값과 일치한다`() {
        val lastDay = Calendar.getInstance().getActualMaximum(Calendar.DATE)
        Assert.assertEquals(calendarModelImpl.getDayList().last().value, lastDay)
    }

    @Test
    fun `26_getDayList()의 마지막 요소의 Value는 2021년07월의 마지막 Day값과 일치한다`() {
        calendarModelImpl.setMonth(7)
        val lastDay = Calendar.getInstance().getActualMaximum(Calendar.DATE)
        Assert.assertEquals(calendarModelImpl.getDayList().last().value, lastDay)
    }


    @After
    fun finish() {
    }


}