package com.example.test

import android.app.Activity
import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_layout.view.*
import kotlinx.android.synthetic.main.item_layout.view.*

class CustomAdapter : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))
    }

    // set static count of items
    override fun getItemCount(): Int {
        return 3
    }

    // set default value in every item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.etTipsAmount.setText(0.0.toString())
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val etTipsAmount = view.etTipsAmount

    }
}