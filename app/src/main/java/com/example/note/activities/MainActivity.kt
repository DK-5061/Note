package com.example.note.activities


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.note.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val KEY_EDITOR = "notes"
        const val KEY_SHARED_PREFERENCES = "prefID"
        const val LABEL_TEXT = "Saved"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onButtonClick(v: View?) {

        sharedPreferences = getSharedPreferences(KEY_SHARED_PREFERENCES, MODE_PRIVATE)

        val noteEditText = findViewById<View>(R.id.noteID) as EditText

        val notes: ArrayList<String> = ArrayList()
        notes.addAll(getNotes())
        notes.add(noteEditText.text.toString())
        saveNotes(notes)

        val label = findViewById<View>(R.id.labelID) as TextView
        label.text = LABEL_TEXT

        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    private fun getNotes(): Collection<String> {
        val gson = Gson()
        val json = sharedPreferences.getString(KEY_EDITOR, KEY_EDITOR)
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveNotes(list: ArrayList<String>) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(KEY_EDITOR, json)
        editor.apply()
    }
}