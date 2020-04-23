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
     * the listener for cash change event
     */
    var onTipoutCashChangedListener: ((itemPosition: Int, newCashValue: Double, previousCashValue: Double) -> Unit)? = null

    /**
     * the listener for tapping on "remove" button
     */
    var onTipoutDeleteListener: ((itemPosition: Int, itemCashValue: Double) -> Unit)? = null

    /**
     * holds previous cash value for useful counting of tipout params
     */
    var previousCashValue = 0.0

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
            val context = containerView.context

            item.previousCashValue = 0.0
            containerView.etTipsAmount.setText(0.0.toString())
            containerView.etTipsAmount.isEnabled = item.isEnabled

            containerView.etTipsAmount.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    v.clearFocus()
                    (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let {
                        it.hideSoftInputFromWindow(v.windowToken, 0)
                    }
                    return@setOnKeyListener true
                }
                false
            }
            containerView.etTipsAmount.setOnFocusChangeListener { v, hasFocus ->
                if (!hasFocus) validateCashField(v as EditText, item)
            }
        }

        override fun unbindView(item: ItemItem) {
            containerView.etTipsAmount.setOnKeyListener(null)
        }

        /**
         * Validates the cash edittext [view] value of [item]
         */
        private fun validateCashField(view: EditText, item: ItemItem) {
            view.text?.toString()?.validateCashString()?.let {
                item.onTipoutCashChangedListener?.invoke(adapterPosition, it, item.previousCashValue)
                item.previousCashValue = it
                view.setText(it.toString())
            }
        }

        fun String.validateCashString() : Double {
            return 0.0
        }
    }
}