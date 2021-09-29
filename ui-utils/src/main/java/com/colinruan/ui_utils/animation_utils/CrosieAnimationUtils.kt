package com.colinruan.ui_utils.animation_utils

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewGroup

import android.view.ViewPropertyAnimator

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Scene
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.google.android.material.bottomsheet.BottomSheetBehavior

//info: for layout hiding/showing with animation DONE
fun ViewPropertyAnimator.makeListener(onStart : (animation : Animator?) -> Unit = {},
                                      onEnd : (animation : Animator?) -> Unit = {},
                                      onCancel : (animation : Animator?) -> Unit = {},
                                      onRepeat : (animation : Animator?) -> Unit = {}) : ViewPropertyAnimator {
    return this.setListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {
            onStart(animation)
        }

        override fun onAnimationEnd(animation: Animator?) {
            onEnd(animation)
        }

        override fun onAnimationCancel(animation: Animator?) {
            onCancel(animation)
        }

        override fun onAnimationRepeat(animation: Animator?) {
            onRepeat(animation)
        }

    })
}
// info: for transition animation listeners DONE
fun Transition.makeListener(onStart : (transition : Transition) -> Unit = {},
                            onEnd : (transition : Transition) -> Unit = {},
                            onCancel : (transition : Transition) -> Unit = {},
                            onPause : (transition : Transition) -> Unit = {},
                            onResume : (transition : Transition) -> Unit = {}) : Transition {
    this.addListener(object : Transition.TransitionListener {
        override fun onTransitionStart(transition: Transition) {
            onStart(transition)
        }

        override fun onTransitionEnd(transition: Transition) {
            onEnd(transition)
        }

        override fun onTransitionCancel(transition: Transition) {
            onCancel(transition)
        }

        override fun onTransitionPause(transition: Transition) {
            onPause(transition)
        }

        override fun onTransitionResume(transition: Transition) {
            onResume(transition)
        }

    })
    return this

}
//info : for drawer layout animation listener DONE
fun DrawerLayout.makeListener(onSlide : (drawerView: View, slideOffset: Float) -> Unit = { drawerView, slideoOffSet ->},
                              onOpened : (drawerView: View) -> Unit = {},
                              onClosed : (drawerView: View) -> Unit = {},
                              onStateChanged : (newState : Int) -> Unit = {}) {
    this.addDrawerListener(object : DrawerLayout.DrawerListener {
        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            onSlide(drawerView, slideOffset)
        }

        override fun onDrawerOpened(drawerView: View) {
            onOpened(drawerView)
        }

        override fun onDrawerClosed(drawerView: View) {
            onClosed(drawerView)
        }

        override fun onDrawerStateChanged(newState: Int) {
            onStateChanged(newState)
        }

    })
}

//info: fade out hide layout simple animation
fun View.hide(animationDuration : Long = 500L, hiddenOpacity : Float = 0.0f) {
    this.animate().apply {
        duration = animationDuration
        alpha(hiddenOpacity)
        makeListener(onEnd = {this@hide.visibility = View.INVISIBLE})
    }
}
//info: fade in hide layout simple animation
fun View.show(animationDuration : Long, visibleOpacity : Float = 1.0f) {
    this.animate().apply {
        duration = animationDuration
        alpha(visibleOpacity)
        makeListener(onEnd = {this@show.visibility = View.VISIBLE})
    }
}

//info: for bottomsheet animation listeners DONE
fun BottomSheetBehavior<ConstraintLayout>.makeBottomSheetCallback(onStateChanged : (bottomSheet : View, newState : Int) -> Unit = { bottomSheet, newState ->},
                                                                  onSlide : (bottomShhet : View, slideOffset : Float) -> Unit = { bottomSheet, slideOffset ->}) {
    this.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            onStateChanged(bottomSheet, newState)
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            onSlide(bottomSheet, slideOffset)
        }

    })
}

//info: for recyclerview ItemTouchHelper simple callback listeners DONE
fun makeItemTouchCallback(dragDirs : Int = 0, swipeDirs : Int = 0, longPressEnabled : Boolean = false, onMove : (recyclerView : RecyclerView, viewHolder : RecyclerView.ViewHolder, target : RecyclerView.ViewHolder) -> Boolean = { recyclerView, viewHolder, target -> false},
                          getMovementFlags : (recyclerView : RecyclerView, viewHolder : RecyclerView.ViewHolder, dragDirs : Int, swipeDirs : Int) -> Int = { recyclerView, viewHolder, dragDirs, swipeDirs -> 0},
                          doNotAllowDragIf : (recyclerView : RecyclerView, viewHolder : RecyclerView.ViewHolder) -> Boolean = { recyclerView, viewHolder -> false},
                          doNotAllowSwipeIf : (recyclerView : RecyclerView, viewHolder : RecyclerView.ViewHolder) -> Boolean = { recyclerView, viewHolder -> false},
                          onSwiped : (viewHolder : RecyclerView.ViewHolder, direction : Int) -> Unit = { viewHolder, direction -> },
                          onClearView : (recyclerView : RecyclerView, viewHolder : RecyclerView.ViewHolder) -> Unit = { recyclerView, viewHolder ->}) : ItemTouchHelper.SimpleCallback {

    return object : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return onMove(recyclerView, viewHolder, target)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            onSwiped(viewHolder, direction)
        }

        override fun isLongPressDragEnabled(): Boolean {
            return longPressEnabled
        }

        override fun getDragDirs(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            if(doNotAllowDragIf(recyclerView, viewHolder)) return 0
            return super.getDragDirs(recyclerView, viewHolder)
        }

        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            return getMovementFlags(recyclerView, viewHolder, dragDirs, swipeDirs)
        }

        override fun getSwipeDirs(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
            if (doNotAllowSwipeIf(recyclerView, viewHolder)) return 0
            return super.getSwipeDirs(recyclerView, viewHolder)
        }

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            onClearView(recyclerView, viewHolder)
        }

    }

}

//info: shortcut function for textView simple value animator for Float or Int
fun TextView.setSimpleValueAnimator(valueType : String = "FLOAT", toValue : Float, animationDuration : Long, onAnimating : (valueAnimator : ValueAnimator) -> Unit = {}) : ValueAnimator {
    val intType = valueType == "INTEGER"

    val animator = if(intType) ValueAnimator.ofInt(this.text.toString().toInt(), toValue.toInt()) else ValueAnimator.ofFloat(this.text.toString().toFloat(), toValue)
    animator.duration = animationDuration
    animator.addUpdateListener {
        onAnimating(it)
        this.text = animator.animatedValue.toString()
    }
    return animator
}

//info: generic View within ConstraintLayout animation shortcut func
fun View.makeSimpleConstraintAnimation(transitionSet : Transition, invokeAfterApply : (thisView : View) -> Unit = {}, setUpConstraintSet : (thisView : View, constraintSet : ConstraintSet) -> ConstraintSet) {
    val constraintParent = this.parent as ConstraintLayout
    TransitionManager.go(Scene(constraintParent as ViewGroup), transitionSet)
    val constraintSet = ConstraintSet()
    setUpConstraintSet(this, constraintSet).applyTo(constraintParent)
    invokeAfterApply(this)
}

