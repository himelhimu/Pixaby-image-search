package com.example.sabbir.pixabaysearch.di

import android.content.Context
import com.example.sabbir.pixabaysearch.MyApplication
import com.example.sabbir.pixabaysearch.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by himelhimu on 11/17/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */
@Singleton
@Component(modules = [NetworkModule::class, AndroidSupportInjectionModule::class,
    ViewModelModule::class])
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}