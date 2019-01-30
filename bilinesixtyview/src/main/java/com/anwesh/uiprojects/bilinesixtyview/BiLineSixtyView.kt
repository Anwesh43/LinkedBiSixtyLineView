package com.anwesh.uiprojects.bilinesixtyview

/**
 * Created by anweshmishra on 30/01/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val sizeFactor : Float = 2.9f
val strokeFactor : Int = 90
val foreColor : Int = Color.parseColor("#34CF93")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b .inverse()
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * scGap * dir

fun Canvas.drawSixtyLine(i : Int, size : Float, sc1 : Float, sc2 : Float, paint : Paint) {
    val sf : Float = 1f - 2 * i
    val deg : Float = 60f
    save()
    translate(-size * Math.sin(deg * Math.PI / 180).toFloat(), -size + i * 2 * size)
    rotate(-deg * sf * sc1)
    drawLine(0f, 2 * size * sf * sc2, 0f, 2 * size * sf, paint)
    restore()
}

fun Canvas.drawBLSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    save()
    translate(w/2, gap * (i + 1))
    for (j in 0..(lines - 1)) {
        save()
        drawSixtyLine(j, size, sc1.divideScale(j, 2), sc2.divideScale(j, 1), paint)
        restore()
    }
    restore()
}

class BiLineSixtyView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }
}