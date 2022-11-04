package com.imt.fil.filmsapp.pages

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.imt.fil.filmsapp.R
import com.imt.fil.filmsapp.models.Movie

class SearchListAdapter(private val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {
    var dataSet: List<Movie> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            notifyDataSetChanged()
            field = value
        }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleSearch: TextView
        init {
            with(view) {
                titleSearch = findViewById(R.id.titleSearch)
                setOnClickListener{
                    onClick(dataSet[adapterPosition])
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_search_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleSearch.text = dataSet[position].title
    }
    override fun getItemCount(): Int =
        dataSet.size
}