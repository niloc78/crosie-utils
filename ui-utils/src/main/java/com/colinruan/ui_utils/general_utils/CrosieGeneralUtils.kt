package com.colinruan.ui_utils.general_utils

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


//info : for recyclerView diffUtil.calculateDiff
fun makeDiffUtilListener(getOldListSize : () -> Int = {0},
                         getNewListSize : () -> Int = {0},
                         areItemsTheSame : (oldItemPosition : Int, newItemPosition : Int) -> Boolean,
                         areContentsTheSame : (oldItemPosition : Int, newItemPosition : Int) -> Boolean) : DiffUtil.DiffResult {
    return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return getOldListSize()
        }

        override fun getNewListSize(): Int {
            return getNewListSize()
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return areItemsTheSame(oldItemPosition, newItemPosition)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return areContentsTheSame(oldItemPosition, newItemPosition)
        }

    })

}

//info: short cut function to set up recyclerview with adapter
fun RecyclerView.setUp(adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>, layoutManager : RecyclerView.LayoutManager) : RecyclerView {
    this.adapter = adapter
    this.layoutManager = layoutManager
    return this
}

//info: shortcut for toast
fun makeToast(msg : String, context : Context, dur : Int) : Toast {
    return Toast.makeText(context, msg, dur)
}

// info: shortcut for Snackbar
fun makeSnackBar(anchor : View, msg : String, dur : Int, actionName : String? = null, action : (view : View) -> Unit) : Snackbar {
    return Snackbar.make(anchor, msg, dur).setAction(actionName) {
        action(it)
    }
}

//info: shortcut fun for longclick with custom time requiring coroutines
fun View.setOnLongClickListener(overlapClickAndLongClick : Boolean = false,longClickDuration : Long = 1000L, onLongClick : (view : View) -> Unit, onPressing : (view : View) -> Unit = {},
                                onPressCanceled : (view : View) -> Unit = {}) {
    this.setOnTouchListener { v, event ->
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (overlapClickAndLongClick) this.performClick()
                onPressing(v)
                CoroutineScope(Main).launch {
                    delay(longClickDuration)
                    onLongClick.invoke(v)
                }

            }
            MotionEvent.ACTION_UP -> {
                onPressCanceled(v)

            }
        }
        true
    }
}