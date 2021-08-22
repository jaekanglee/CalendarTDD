package com.example.calendarmvvm.model

enum class DayListType() {
    BLANK,DAY
}

data class DayListEntity(
    val type :DayListType?=null,
    val value : Int
)
