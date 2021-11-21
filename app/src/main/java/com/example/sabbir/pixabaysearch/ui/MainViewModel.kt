package com.example.sabbir.pixabaysearch.ui

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sabbir.pixabaysearch.models.ImageHit
import com.example.sabbir.pixabaysearch.models.PixabyApiQueryResponse
import com.example.sabbir.pixabaysearch.network.PixabyApiService
import com.example.sabbir.pixabaysearch.utils.Event
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by himelhimu on 11/17/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */

class MainViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var apiService: PixabyApiService

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _items = MutableLiveData<PixabyApiQueryResponse>().apply { value = PixabyApiQueryResponse(0,0,
        emptyList()) }
    val items: LiveData<PixabyApiQueryResponse> = _items

    private val _openTaskEvent = MutableLiveData<Event<ImageHit>>()
    val openTaskEvent: LiveData<Event<ImageHit>> = _openTaskEvent

    fun searchForImages(query: String) {
        _dataLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {

            val pixabyApiQueryResponse = apiService.getImagesForQuery(query)
                _items.postValue(pixabyApiQueryResponse)
            _dataLoading.postValue(false)
        }
    }

    fun openImageDetails(image:ImageHit){
        _openTaskEvent.value = Event(image)
    }


    /**
     * simply saving it to shared prefs as it's not clear if or how should access the saved data
     * if later access/modification is required than should persist the data on Room db.
     * */

    fun cacheTheLastData(itemList:List<ImageHit>,query:String,prefs:SharedPreferences){
        viewModelScope.launch(Dispatchers.IO) {
            val jsonStr = Gson().toJson(itemList)
            /*val map = HashMap<String,String>().apply {
                put(query,jsonStr)
            }
            val dataJson = Gson().toJson(map)*/
            prefs.edit().putString(query,jsonStr).apply()
        }
    }

}