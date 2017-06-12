package com.lynlab.restbus.api

import com.lynlab.restbus.model.BusRoute
import com.lynlab.restbus.model.BusStation
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
                .baseUrl("http://restbus.lynlab.co.kr")
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

    /**
     * GET /v1/stations?routeId={routeId}
     * @param routeId 노선 ID
     */
    fun getStations(routeId: Int): Observable<List<BusStation>> {
        return restApiService.getStations(routeId)
    }

}