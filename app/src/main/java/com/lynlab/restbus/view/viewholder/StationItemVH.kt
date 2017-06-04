package com.lynlab.restbus.view.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.lynlab.restbus.R

/**
 * 노선 검색결과 RecyclerView 뷰 홀더
 */
class StationItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var nameTextView: TextView? = null
    var idTextView: TextView? = null

    init {
        nameTextView = itemView.findViewById(R.id.textview_item_station_list_name) as TextView
        idTextView = itemView.findViewById(R.id.textview_item_station_list_id) as TextView
    }

}