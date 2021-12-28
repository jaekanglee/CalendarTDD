package com.example.calendarmvvm.di

import com.example.calendarmvvm.model.CalendarModelImpl
import com.example.calendarmvvm.model.ICalendarModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ModelModule {

    @Singleton
    @Provides
    fun provideCalendarModel(): ICalendarModel {
        return CalendarModelImpl()
    }
}
