package com.example.maintainingsharedpreferences

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
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
        Log.d("ActivityLifeCycle", "onCreate")
        val button = findViewById<Button>(R.id.loadImageButton)
        image = findViewById(R.id.savedImage)
        userText = findViewById(R.id.savedText)

        sharedPreferences = getSharedPreferences("ImageAndText", MODE_PRIVATE);

        loadSavedImage()
        loadSavedText()

        button.setOnClickListener {
            loadImage()
            saveImage()
            saveText()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityLifeCycle", "onDestroy")
    }

    private fun saveText() {
        val editor = sharedPreferences.edit()
        editor.putString("text", userText.text.toString())
        editor.apply()
    }

    private fun saveImage() {
        val editor = sharedPreferences.edit()
        editor.putInt("image", image.drawable?.let { image.tag as? Int ?: -1 } ?: -1)
        editor.apply()
    }


    private fun loadImage() {
        val random = Random.nextInt(1, 6)
        val resId = resources.getIdentifier("drawable_$random", "drawable", packageName)
        val drawable: Drawable? = resources.getDrawable(resId, null)
        image.setImageDrawable(drawable)
        image.tag = random
    }

    private fun loadSavedText() {
        val text = sharedPreferences.getString("text", "")
        userText.setText(text)    }

    private fun loadSavedImage() {
        val imageNum = sharedPreferences.getInt("image", -1)
        val imageId = "drawable_$imageNum"
        if (imageNum != -1) {
            val resId = resources.getIdentifier(imageId, "drawable", packageName)
            val drawable: Drawable? = resources.getDrawable(resId, null)
            image.setImageDrawable(drawable)
        }
    }
}