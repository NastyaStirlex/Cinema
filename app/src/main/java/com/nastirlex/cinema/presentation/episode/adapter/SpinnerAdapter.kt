package com.nastirlex.cinema.presentation.episode.adapter

import android.app.Application
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class SpinnerAdapter(application: Application, private val collections: Array<String>) :
    ArrayAdapter<String?>(application, com.nastirlex.cinema.R.layout.item_collections_spinner, collections) {

}