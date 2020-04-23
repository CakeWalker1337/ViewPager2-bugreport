package com.example.test

import android.app.Activity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_layout.view.*

/**
 * An item class represents tipout of user or group
 *
 * @property tipout tipout of user or group
 */
class ItemItem() : AbstractItem<ItemItem.ViewHolder>() {

    /**
     * The type of item. id of parent recycler
     */
    override val type: Int
        get() = R.id.item_id

    override val layoutRes: Int
        get() = R.layout.item_layout


    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    /**
     * ViewHolder for storing item information and binding it to view.
     */
    class ViewHolder(override val containerView: View) : FastAdapter.ViewHolder<ItemItem>(containerView), LayoutContainer {

        override fun bindView(item: ItemItem, payloads: List<Any>) {
            containerView.etTipsAmount.setText(0.0.toString())

        }

        override fun unbindView(item: ItemItem) {
            containerView.etTipsAmount.setOnKeyListener(null)
        }

    }
}