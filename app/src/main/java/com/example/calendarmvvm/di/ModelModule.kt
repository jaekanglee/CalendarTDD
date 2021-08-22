package com.example.calendarmvvm.di

import com.example.calendarmvvm.model.CalendarModel
import com.example.calendarmvvm.model.CalendarModelImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object  ModelModule {

    @Provides
    fun provideCalendarModel():CalendarModel{
        return CalendarModelImpl()
    }
}