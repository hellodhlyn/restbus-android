package com.lynlab.restbus.api

import com.lynlab.restbus.model.BusRoute
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * RESTful API 인터페이스
 */
interface RestApiService {

    @GET("/v1/hello")
    fun getHello(): Observable<String>

    @GET("/v1/routes")
    fun getRoutes(@Query("query") query: String): Observable<List<BusRoute>>

}