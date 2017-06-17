package com.lynlab.restbus.model

import com.google.gson.annotations.SerializedName

/**
 * 버스 도착정보 모델
 */
class BusArrival {

    @SerializedName("before")
    var before: Int

    @SerializedName("bus_number")
    var busNumber: String

    @SerializedName("is_full")
    var isFull: Boolean

    @SerializedName("is_last")
    var isLast: Boolean

    @SerializedName("people_count")
    var peopleCount: Int

    @SerializedName("station_name")
    var stationName: String

    @SerializedName("waiting_time")
    var waitingTime: Int

    constructor(before: Int, busNumber: String, isFull: Boolean, isLast: Boolean, peopleCount: Int, stationName: String, waitingTime: Int) {
        this.before = before
        this.busNumber = busNumber
        this.isFull = isFull
        this.isLast = isLast
        this.peopleCount = peopleCount
        this.stationName = stationName
        this.waitingTime = waitingTime
    }
}