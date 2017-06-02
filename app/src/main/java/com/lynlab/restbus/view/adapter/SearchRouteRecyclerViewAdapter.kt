package com.lynlab.restbus.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lynlab.restbus.R
import com.lynlab.restbus.model.BusRoute
import com.lynlab.restbus.view.viewholder.SearchRouteRecyclerVH
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * 노선 검색결과 RecyclerView 어댑터
 */
class SearchRouteRecyclerViewAdapter : RecyclerView.Adapter<SearchRouteRecyclerVH>() {

    private val onItemClickSubject: PublishSubject<Int> = PublishSubject.create()
    private var items: List<BusRoute> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRouteRecyclerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_search_route, parent, false)
        return SearchRouteRecyclerVH(view)
    }

    override fun onBindViewHolder(holder: SearchRouteRecyclerVH, position: Int) {
        holder.itemView.setOnClickListener({ onItemClickSubject.onNext(items[position].routeId) })

        holder.nameTextView!!.text = items[position].routeName
        holder.pathTextView!!.text = "${items[position].firstStationName} → ${items[position].lastStationName}"
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getOnItemClickObservable(): Observable<Int> {
        return onItemClickSubject
    }

    fun setItems(items: List<BusRoute>) {
        this.items = items
    }

}