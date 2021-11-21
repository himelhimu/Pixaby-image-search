package com.example.sabbir.pixabaysearch

import android.app.Application
import com.example.sabbir.pixabaysearch.di.AppComponent
import com.example.sabbir.pixabaysearch.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * Created by himelhimu on 11/17/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */
open class MyApplication : DaggerApplication(){


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(applicationContext)
    }


}