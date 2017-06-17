package com.lynlab.restbus.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.lynlab.restbus.R
import com.lynlab.restbus.api.RestApi
import com.lynlab.restbus.model.BusArrival
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * 버스 도착정보 프래그먼트
 */
class ArrivalFragment(nextPageSubject: PublishSubject<Any?>) : Fragment() {

    private val restApi = RestApi.instance

    private var stationTextView : TextView? = null
    private var arrivalTextView : TextView? = null

    init {
        nextPageSubject.subscribe({ args ->
            if (args is StationListFragment.OnClickArgs) {
                requestArrivalInfo(args)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_arrival, container, false)

        stationTextView = view.findViewById(R.id.textview_fragment_arrival_station) as TextView
        arrivalTextView = view.findViewById(R.id.textview_fragment_arrival_time) as TextView

        return view
    }

    fun requestArrivalInfo(args: StationListFragment.OnClickArgs) {
        restApi.getArrivals(routeId = args.routeId, stationId = args.stationId, order = args.order)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { arrivals ->
                            stationTextView?.text = generateStationText(arrivals[0])
                            arrivalTextView?.text = generateArrivalText(arrivals[0].waitingTime)
                        },
                        { e ->
                            Toast.makeText(context, R.string.toast_error_network, Toast.LENGTH_SHORT).show()
                            Log.e(this.javaClass.simpleName, "Api request error", e)
                        }
                )
    }

    private fun generateStationText(arrival: BusArrival) : String {
        return arrival.stationName + (if (arrival.peopleCount > 0) " (${arrival.peopleCount}명 탑승)" else "")
    }

    private fun generateArrivalText(seconds: Int): String {
        if (seconds >= 60) {
            return "${seconds / 60}분 ${seconds % 60}초 뒤에 도착합니다."
        } else {
            return "잠시 후 도착합니다."
        }
    }

}