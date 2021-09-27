package com.kieronquinn.app.classicpowermenu.utils.extensions

import android.graphics.Point
import android.graphics.Rect
import android.view.View
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.math.roundToInt

suspend fun View.awaitPost() = suspendCancellableCoroutine<View> {
    post {
        if(isAttachedToWindow){
            it.resume(this)
        }else{
            it.cancel()
        }
    }
}

fun View.getCenter(): Point {
    IntArray(2).apply {
        getLocationOnScreen(this)
    }
    return Point((x + (width / 2f)).roundToInt(), (y + (height / 2f)).roundToInt())
}

fun View.contains(point: Point): Boolean {
    val position = IntArray(2).apply {
        getLocationOnScreen(this)
    }.run {
        Rect(this[0], this[1], this[0] + width, this[0] + height)
    }
    return position.contains(point.x, point.y)
}