package com.lynlab.restbus.api

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * RESTful API 모듈
 */
class RestApi {

    private val restApiService: RestApiService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://apis.lynlab.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        restApiService = retrofit.create(RestApiService::class.java)
    }

    fun getHello(): Observable<String> {
        return restApiService.hello()
    }

}