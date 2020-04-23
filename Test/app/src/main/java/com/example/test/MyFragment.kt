package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.fragment_layout.*

class MyFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_layout, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // setup external edittext (this one is not located in recycler view)
        val value = arguments?.getInt("VALUE") ?: -1
        etTitle.setText(value.toString())

        // setup recycler view with custom adapter.
        rvItems.layoutManager = LinearLayoutManager(requireContext())

        // No matter which adapter is used, the bug is still existed (FastAdapter works the same)
        val adapter = CustomAdapter()

        // an example of reproducing the bug using FastAdapter (uncomment it)
//        val adapter = FastItemAdapter<ItemItem>()
//        adapter.setNewList(listOf(ItemItem(), ItemItem(), ItemItem()))
        
        rvItems.adapter = adapter
    }

    companion object {
        fun create(num: Int) =
            MyFragment().apply {
                arguments = Bundle().apply {
                    putInt("VALUE", num)
                }
            }

    }

}