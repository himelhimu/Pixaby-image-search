package com.example.sabbir.pixabaysearch.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by himelhimu on 11/17/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */
data class ImageHit (
    @SerializedName("id") val id : Int,
    @SerializedName("pageURL") val pageURL : String,
    @SerializedName("type") val type : String,
    @SerializedName("tags") val tags : String,
    @SerializedName("previewURL") val previewURL : String,
    @SerializedName("previewWidth") val previewWidth : Int,
    @SerializedName("previewHeight") val previewHeight : Int,
    @SerializedName("webformatURL") val webformatURL : String,
    @SerializedName("webformatWidth") val webformatWidth : Int,
    @SerializedName("webformatHeight") val webformatHeight : Int,
    @SerializedName("largeImageURL") val largeImageURL : String,
    @SerializedName("fullHDURL") val fullHDURL : String,
    @SerializedName("imageURL") val imageURL : String,
    @SerializedName("imageWidth") val imageWidth : Int,
    @SerializedName("imageHeight") val imageHeight : Int,
    @SerializedName("imageSize") val imageSize : Int,
    @SerializedName("views") val views : Int,
    @SerializedName("downloads") val downloads : Int,
    @SerializedName("likes") val likes : Int,
    @SerializedName("comments") val comments : Int,
    @SerializedName("user_id") val user_id : Int,
    @SerializedName("user") val user : String,
    @SerializedName("userImageURL") val userImageURL : String
) : Serializable
