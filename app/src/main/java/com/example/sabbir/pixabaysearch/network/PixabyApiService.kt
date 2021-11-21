package com.example.sabbir.pixabaysearch.network

import com.example.sabbir.pixabaysearch.models.PixabyApiQueryResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by himelhimu on 11/17/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */

const val API_BASE_URL="https://pixabay.com/api/"

interface PixabyApiService {

    @GET(".")
    suspend fun getImagesForQuery(@Query("q")query:String) : PixabyApiQueryResponse
}