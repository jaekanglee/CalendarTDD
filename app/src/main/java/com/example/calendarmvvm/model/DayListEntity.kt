package com.example.calendarmvvm.model

enum class DayListType() {
    BLANK,DAY, DOW; //DAYOFWEEKS
}

data class DayListEntity(
    val type :DayListType?=null,
    val value : String?
)
