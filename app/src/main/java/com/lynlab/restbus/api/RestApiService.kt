package com.lynlab.restbus.api

import io.reactivex.Observable
import retrofit2.http.GET

/**
 * RESTful API 인터페이스
 */
interface RestApiService {

    @GET("/v1/hello")
    fun hello(): Observable<String>

}