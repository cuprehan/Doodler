package com.example.doodler

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape


class MainActivity : AppCompatActivity() {
    lateinit var doodleView: DoodleView
    lateinit var brushSizeSeekBar: SeekBar
    lateinit var opacitySeekBar: SeekBar
    lateinit var colorButton: Button
    lateinit var clearButton: Button
    lateinit var redoButton: Button
    lateinit var undoButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doodleView = findViewById(R.id.doodleView) as DoodleView
        brushSizeSeekBar = findViewById(R.id.brush_size)
        opacitySeekBar = findViewById(R.id.opacity)
        colorButton = findViewById(R.id.color_picker)
        clearButton = findViewById(R.id.clear_button)
        redoButton = findViewById(R.id.redo_button)
        undoButton = findViewById(R.id.undo_button)

        brushSizeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                doodleView.setBrushSize(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        opacitySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                doodleView.setOpacity(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        colorButton.setOnClickListener {
            val currentColor = doodleView.getColor()

            ColorPickerDialog
                .Builder(this)        // Pass Activity Instance
                .setTitle("Pick Theme")           // Default "Choose Color"
                .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                .setDefaultColor(currentColor)     // Pass Default Color
                .setColorListener { color, _ ->
                    // Handle Color Selection
                    doodleView.setColor(color)
                }
                .show()
        }

        clearButton.setOnClickListener {
            doodleView.clear()
        }

        undoButton.setOnClickListener {
            doodleView.undo()
        }

        redoButton.setOnClickListener {
            doodleView.redo()
        }
    }
}
