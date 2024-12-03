package com.example.doodler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View


class DoodleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val path = Path()
    private val paths = mutableListOf<Pair<Path, Paint>>()
    private val redoPaths = mutableListOf<Pair<Path, Paint>>()

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DoodleView)
        paint.color = attributes.getColor(R.styleable.DoodleView_strokeColor, Color.BLACK)
        paint.strokeWidth = attributes.getDimension(R.styleable.DoodleView_strokeWidth, 10f)
        attributes.recycle()

        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for ((p,paint) in paths) {
            canvas.drawPath(p, paint)
        }
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> path.moveTo(event.x, event.y)
            MotionEvent.ACTION_MOVE -> path.lineTo(event.x, event.y)
            MotionEvent.ACTION_UP -> {
                paths.add(Pair(Path(path), Paint(paint)))
                path.reset()
                redoPaths.clear()
            }
        }
        invalidate()
        return true
    }

    fun clear() {
        paths.clear()
        redoPaths.clear()
        invalidate()
    }

    fun undo() {
        if (paths.isNotEmpty()) {
            redoPaths.add(paths.removeAt(paths.size - 1))
            invalidate()
        }
    }

    fun redo() {
        if (redoPaths.isNotEmpty()) {
            paths.add(redoPaths.removeAt(redoPaths.size - 1))
            invalidate()
        }
    }

    fun setBrushSize(size: Float) {
        paint.strokeWidth = size
    }

    fun setOpacity(opacity: Int) {
        paint.alpha = opacity
    }

    fun getColor(): Int {
        return paint.color
    }

    fun setColor(color: Int) {
        paint.color = color
        invalidate()
    }
}
