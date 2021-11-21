package com.example.sabbir.pixabaysearch.models

import com.google.gson.annotations.SerializedName

/**
 * Created by himelhimu on 11/17/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individual library's used in here might have their own licensing.
 */
data class PixabyApiQueryResponse(
    @SerializedName("total")val total:Int,
    @SerializedName("totalHits")val totalHits:Int,
    @SerializedName("hits")val imageHitList:List<ImageHit>
)
