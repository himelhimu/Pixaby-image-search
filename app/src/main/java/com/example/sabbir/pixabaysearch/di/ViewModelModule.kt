package com.example.sabbir.pixabaysearch.di

import androidx.lifecycle.ViewModel
import com.example.sabbir.pixabaysearch.ui.ImageListFragment
import com.example.sabbir.pixabaysearch.ui.MainViewModel
import com.example.sabbir.pixabaysearch.ui.ImageDetailsFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by himelhimu on 11/18/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */
@Module
abstract class ViewModelModule {

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    abstract fun provideListFragmentFragment(): ImageListFragment

    @ContributesAndroidInjector(modules = [
        ViewModelBuilder::class
    ])
    abstract fun provideImageDetailsFragment(): ImageDetailsFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel):ViewModel



}