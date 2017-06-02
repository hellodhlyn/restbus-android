package com.lynlab.restbus.api

import com.lynlab.restbus.model.BusRoute
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * RESTful API 모듈
 */
class RestApi {

    private object Holder { var INSTANCE = RestApi() }
    companion object {
        val instance: RestApi by lazy { Holder.INSTANCE }
    }

    private val restApiService: RestApiService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://apis.lynlab.co.kr")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        restApiService = retrofit.create(RestApiService::class.java)
    }

    /**
     * GET /v1/hello
     */
    fun getHello(): Observable<String> {
        return restApiService.getHello()
    }

    /**
     * GET /v1/routes?query={query}
     * @param query 노선 이름
     */
    fun getRoutes(query: String): Observable<List<BusRoute>> {
        return restApiService.getRoutes(query)
    }

}