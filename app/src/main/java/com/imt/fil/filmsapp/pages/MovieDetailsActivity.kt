package com.imt.fil.filmsapp.pages

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import coil.load
import com.imt.fil.filmsapp.R
import com.imt.fil.filmsapp.services.MovieService
import kotlin.math.round

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var movieService: MovieService

    companion object {
        const val INTENT_PARAM_ID = "intent_param_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieService = MovieService(applicationContext)
    }

    override fun onStart() {
        super.onStart()

        val id = intent.getIntExtra(INTENT_PARAM_ID, -1)
        movieService.getMovieDetail(id, success = { movie ->
            movie?.let {
                findViewById<ImageView>(R.id.backdrop).load("https://image.tmdb.org/t/p/original/${it.backdropUrl}")
                findViewById<TextView>(R.id.title).text = it.title
                findViewById<TextView>(R.id.vote).text = String.format("%.1f / 10", it.averageVote)
                findViewById<TextView>(R.id.overview).text = it.overview
                findViewById<TextView>(R.id.cast).text = it.cast.map { cast ->
                    cast.name
                }.joinToString(" - ")
                findViewById<ToggleButton>(R.id.favoriteBtn).isChecked = movie.favorite
                findViewById<ToggleButton>(R.id.favoriteBtn).setOnCheckedChangeListener(object : View.OnClickListener,
                    CompoundButton.OnCheckedChangeListener {
                    override fun onClick(p0: View?) {
                        Log.d("setOnCheckedChangeListener", "onClick")
                    }

                    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                        Log.d("setOnCheckedChangeListener", "onCheckedChanged $p1")
                        movieService.toggleFavorite(movie, p1)
                    }
                })
            }
        }, failure = {
        }
        )
    }
}