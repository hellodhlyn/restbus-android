package com.lynlab.restbus.view.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
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
class SearchRouteRecyclerViewAdapter(private var context: Context) : RecyclerView.Adapter<SearchRouteRecyclerVH>() {

    private val onItemClickSubject: PublishSubject<Int> = PublishSubject.create()
    private var items: List<BusRoute> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRouteRecyclerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_search_route, parent, false)
        return SearchRouteRecyclerVH(view)
    }

    override fun onBindViewHolder(holder: SearchRouteRecyclerVH, position: Int) {
        holder.itemView.setOnClickListener({ onItemClickSubject.onNext(items[position].routeId) })

        holder.nameTextView!!.text = items[position].routeName
        holder.nameTextView!!.setTextColor(ContextCompat.getColor(context,
                when (items[position].routeType) {
                    1 -> R.color.font_orange        // 공항 버스
                    3 -> R.color.font_blue          // 간선 버스
                    2, 4 -> R.color.font_green         // 지선 버스
                    5 -> R.color.font_yellow        // 순환 버스
                    6 -> R.color.font_red           // 광역 버스
                    7, 8 -> R.color.font_teal       // 인천/경기 버스
                    else -> R.color.font_black
                }
        ))

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