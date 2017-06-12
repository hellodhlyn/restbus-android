package com.lynlab.restbus.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.lynlab.restbus.R
import com.lynlab.restbus.api.RestApi
import com.lynlab.restbus.view.adapter.StationListRecyclerViewAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * 정류장 목록 프래그먼트
 */
class StationListFragment(nextPageSubject: PublishSubject<Any?>) : Fragment() {

    private val restApi = RestApi.instance
    private val recyclerViewAdapter = StationListRecyclerViewAdapter()
    private var progrssBar: ProgressBar? = null

    init {
        nextPageSubject.subscribe({ arg -> loadStations(arg as Int) })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_station_list, container, false)

        // ProgressBar 설정
        progrssBar = view.findViewById(R.id.progressbar_view_station_list) as ProgressBar

        // RecyclerView 설정
        val recyclerView = view.findViewById(R.id.recycler_view_station_list) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerViewAdapter

        return view
    }

    fun loadStations(routeId: Int) {
        progrssBar?.visibility = View.VISIBLE
        restApi.getStations(routeId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { stations ->
                            recyclerViewAdapter.setItems(stations)
                            recyclerViewAdapter.notifyDataSetChanged()
                        },
                        { e ->
                            Toast.makeText(context, R.string.toast_error_network, Toast.LENGTH_SHORT).show()
                            Log.e(this.javaClass.simpleName, "Api request error", e)
                        },
                        {
                            progrssBar?.visibility = View.GONE
                        }
                )
    }

}