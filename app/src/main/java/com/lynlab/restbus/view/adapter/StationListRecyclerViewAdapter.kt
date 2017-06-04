package com.lynlab.restbus.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lynlab.restbus.R
import com.lynlab.restbus.model.BusStation
import com.lynlab.restbus.view.viewholder.StationItemVH
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * 노선 검색결과 RecyclerView 어댑터
 */
class StationListRecyclerViewAdapter : RecyclerView.Adapter<StationItemVH>() {

    private val onItemClickSubject: PublishSubject<Int> = PublishSubject.create()
    private var items: List<BusStation> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationItemVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_station_list, parent, false)
        return StationItemVH(view)
    }

    override fun onBindViewHolder(holder: StationItemVH, position: Int) {
        holder.itemView.setOnClickListener({ onItemClickSubject.onNext(items[position].stationId) })

        holder.nameTextView!!.text = items[position].stationName
        holder.idTextView!!.text = items[position].stationId.toString()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getOnItemClickObservable(): Observable<Int> {
        return onItemClickSubject
    }

    fun setItems(items: List<BusStation>) {
        this.items = items
    }

}