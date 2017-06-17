package com.lynlab.restbus.model

import com.google.gson.annotations.SerializedName

/**
 * 버스 정류장 모델
 */
class BusStation {

    @SerializedName("station_id")
    var stationId: Int

    @SerializedName("station_num")
    var stationNum: Int

    @SerializedName("sequence")
    var sequence: Int

    @SerializedName("station_name")
    var stationName: String

    @SerializedName("is_turn_station")
    var isTurnStation: Boolean

    @SerializedName("direction")
    var direction: String

    constructor(stationId: Int, stationNum: Int, sequence: Int, stationName: String, isTurnStation: Boolean, direction: String) {
        this.stationId = stationId
        this.stationNum = stationNum
        this.sequence = sequence
        this.stationName = stationName
        this.isTurnStation = isTurnStation
        this.direction = direction
    }

}