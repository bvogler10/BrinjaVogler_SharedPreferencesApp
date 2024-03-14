package com.example.maintainingsharedpreferences

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var userText: EditText
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.loadImageButton)
        image = findViewById(R.id.savedImage)
        userText = findViewById(R.id.savedText)

        sharedPreferences = getSharedPreferences("ImageAndText", MODE_PRIVATE);

//        loadSavedImage()
//        loadSavedText()

        button.setOnClickListener {
            loadImage()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Save state
//        saveImageToPreferences()
//        saveTextToPreferences()
    }



    private fun loadImage() {
        val random = Random.nextInt(1, 6)
        val resId = resources.getIdentifier("drawable_$random", "drawable", packageName)
        val drawable: Drawable? = resources.getDrawable(resId, null)
        image.setImageDrawable(drawable)
    }

    private fun loadSavedText() {
        TODO("Not yet implemented")
    }

    private fun loadSavedImage() {
        TODO("Not yet implemented")
    }

}