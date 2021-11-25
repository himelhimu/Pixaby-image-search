package com.example.sabbir.pixabaysearch.network

import com.example.sabbir.pixabaysearch.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.HttpUrl
import okhttp3.Request


/**
 * Created by himelhimu on 11/17/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */
@Module
class NetworkModule {

    companion object{

        init {
            System.loadLibrary("Keys.c")
        }
    }

    private fun getApiKey():String =  BuildConfig.API_KEY


    external fun getNativeKey1():String

    @Singleton
    @Provides
    fun provideNumbersApiService(okHttpClient: OkHttpClient) : PixabyApiService{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(PixabyApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor):OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)

        return builder.build()
    }

    @Singleton
    @Provides
    fun getInterceptor(): Interceptor {
        return Interceptor() { chain ->
            val original = chain.request()
            val builder = original.newBuilder()

            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", getApiKey())
                .build()
            /*// Request customization: add request headers
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)*/


            val request = builder.header("Content-Type", "application/json")
                .method(original.method, original.body)
                .url(url)
                .build()



            chain.proceed(request)
        }
    }
}