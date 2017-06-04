package com.lynlab.restbus.model

/**
 * 버스 정류장 모델
 */
class BusStation {

    var stationId: Int

    var sequence: Int

    var stationName: String

    var isTurnStation: Boolean

    var direction: String

    constructor(stationId: Int, sequence: Int, stationName: String, isTurnStation: Boolean, direction: String) {
        this.stationId = stationId
        this.sequence = sequence
        this.stationName = stationName
        this.isTurnStation = isTurnStation
        this.direction = direction
    }

}