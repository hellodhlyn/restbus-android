package com.lynlab.restbus.model

import java.util.*

/**
 * 버스 노선 모델
 */
class BusRoute {

    var routeId: Int

    var routeName: String

    /**
     * 1:공항, 3:간선, 4:지선, 5:순환, 6:광역, 7:인천, 8:경기, 9:폐지, 0:공용
     */
    var routeType: Int

    var firstStationName: String?

    var lastStationName: String?

    var firstBusTime: Date?

    var lastBusTime: Date?

    var term: Int

    constructor(routeId: Int, routeName: String, routeType: Int, firstStationName: String?, lastStationName: String?, firstBusTime: Date?, lastBusTime: Date?, term: Int) {
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