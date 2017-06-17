package com.lynlab.restbus.view.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.lynlab.restbus.R
import com.lynlab.restbus.model.BusStation
import com.lynlab.restbus.view.fragment.StationListFragment
import com.lynlab.restbus.view.viewholder.StationItemVH
import io.reactivex.subjects.PublishSubject

/**
 * 노선 검색결과 RecyclerView 어댑터
 */
class StationListRecyclerViewAdapter(private val context: Context) : RecyclerView.Adapter<StationItemVH>() {

    private val onItemClickSubject: PublishSubject<StationListFragment.OnClickArgs> = PublishSubject.create()

    private var items: List<BusStation> = ArrayList()
    private var routeId: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationItemVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view_station_list, parent, false)
        return StationItemVH(view)
    }

    override fun onBindViewHolder(holder: StationItemVH, position: Int) {
        holder.itemView.setOnClickListener({
            onItemClickSubject.onNext(
                    StationListFragment.OnClickArgs(
                            routeId = routeId,
                            stationId = items[position].stationId,
                            order = items[position].sequence
                    )
            )
        })

        val color: Int
        if (items[position].stationNum == -1) {
            // 미정차 정류장
            color = ContextCompat.getColor(context, R.color.font_gray_dark)
            holder.nameTextView!!.text = items[position].stationName
            holder.numTextView!!.text = "미정차"
        } else {
            color = ContextCompat.getColor(context, R.color.font_black)
            holder.nameTextView!!.text = items[position].stationName
            holder.numTextView!!.text = items[position].stationNum.toString()
        }

        holder.nameTextView!!.setTextColor(color)
        holder.numTextView!!.setTextColor(color)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun onItemClick(consumer: (Any) -> Unit) {
        onItemClickSubject.subscribe(consumer)
    }

    fun setItems(items: List<BusStation>) {
        this.items = items
    }

    fun clear() {
        this.items = ArrayList()
    }

    fun setRouteId(routeId: Int) {
        this.routeId = routeId
    }

}