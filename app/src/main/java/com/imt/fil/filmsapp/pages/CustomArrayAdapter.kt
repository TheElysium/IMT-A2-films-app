package com.imt.fil.filmsapp.pages

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.imt.fil.filmsapp.R
import com.imt.fil.filmsapp.models.Genre

class CustomArrayAdapter(context: Context, resource: Int, var items: List<Genre>)
    : ArrayAdapter<Genre>(context, resource, items) {

    val inflater: LayoutInflater = LayoutInflater.from(context)

    // If required, get the ID from your Model. If your desired return value can't be converted to long use getItem(int) instead
    override fun getItemId(position: Int): Long {
        return items[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, container: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.spinner, container, false)
        }
        (view?.findViewById(R.id.genreName) as TextView).text = getItem(position)!!.name
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.spinner, parent, false)
        }
        (view?.findViewById(R.id.genreName) as TextView).text = getItem(position)!!.name
        return view
    }


}