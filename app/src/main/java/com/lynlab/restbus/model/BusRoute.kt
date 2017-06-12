package com.lynlab.restbus.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * 버스 노선 모델
 */
class BusRoute {

    @SerializedName("route_id")
    var routeId: Int

    @SerializedName("route_name")
    var routeName: String

    /**
     * 1:공항, 2:마을, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용
     */
    @SerializedName("route_type")
    var routeType: Int

    @SerializedName("first_station_name")
    var firstStationName: String?

    @SerializedName("last_station_name")
    var lastStationName: String?

    @SerializedName("first_bus_time")
    var firstBusTime: Date

    @SerializedName("last_bus_time")
    var lastBusTime: Date

    @SerializedName("term")
    var term: Int

    constructor(routeId: Int, routeName: String, routeType: Int, firstStationName: String?, lastStationName: String?, firstBusTime: Date, lastBusTime: Date, term: Int) {
        this.routeId = routeId
        this.routeName = routeName
        this.routeType = routeType
        this.firstStationName = firstStationName
        this.lastStationName = lastStationName
        this.firstBusTime = firstBusTime
        this.lastBusTime = lastBusTime
        this.term = term
    }

}