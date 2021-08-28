package com.example.calendarmvvm

import com.example.calendarmvvm.di.ModelModule
import com.example.calendarmvvm.model.CalendarModel
import com.example.calendarmvvm.model.CalendarModelImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ModelModule::class]
)
@InstallIn
abstract class  TestModule {

    @Singleton
    @Binds
    abstract fun bindCalendarModel(model : CalendarModelImpl): CalendarModel
}