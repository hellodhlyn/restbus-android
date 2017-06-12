package com.lynlab.restbus.api

import com.lynlab.restbus.model.BusRoute
import com.lynlab.restbus.model.BusStation
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

    @GET("/v1/stations")
    fun getStations(@Query("route_id") routeId: Int): Observable<List<BusStation>>

}