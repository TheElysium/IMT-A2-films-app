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

class MoviesListAdapter(private val onClick: (Movie) -> Unit, private val onFavoriteToggle: (Movie, Boolean) -> Unit) :
    RecyclerView.Adapter<MoviesListAdapter.ViewHolder>() {
    var dataSet: List<Movie> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            notifyDataSetChanged()
            field = value
        }
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleItem: TextView
        val ratingItem: TextView
        val backdropItem: ImageView
        val favorite: ToggleButton
        init {
            with(view) {
                titleItem = findViewById(R.id.titleItem)
                ratingItem = findViewById(R.id.ratingItem)
                backdropItem = findViewById(R.id.poster)
                favorite = findViewById(R.id.favorite)
                setOnClickListener{
                    onClick(dataSet[adapterPosition])
                }
                favorite.setOnCheckedChangeListener(object : View.OnClickListener,
                    CompoundButton.OnCheckedChangeListener {
                    override fun onClick(p0: View?) {
                        Log.d("setOnCheckedChangeListener", "onClick")
                    }

                    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                        Log.d("setOnCheckedChangeListener", "onCheckedChanged $p1")
                        onFavoriteToggle(dataSet[adapterPosition], p1)
                    }
                })
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_list_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleItem.text = dataSet[position].title
        holder.ratingItem.text = buildString {
        append(dataSet[position].averageVote.toString())
        append("/10")
        }
        holder.backdropItem.load("https://image.tmdb.org/t/p/original/${dataSet[position].backdropUrl}")
        holder.favorite.isChecked = dataSet[position].favorite
    }
    override fun getItemCount(): Int =
        dataSet.size
}