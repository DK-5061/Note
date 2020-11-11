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

        val titleEditText = findViewById<View>(R.id.titleID) as EditText
        val noteEditText = findViewById<View>(R.id.noteID) as EditText

        val note = Note(titleEditText.text.toString(), noteEditText.text.toString())

        val notes: ArrayList<Note> = ArrayList()
        notes.addAll(getNotes())
        notes.add(note)
        saveNotes(notes)

        val label = findViewById<View>(R.id.labelID) as TextView
        label.text = LABEL_TEXT

        val intent = Intent(this, ListActivity::class.java)
        startActivity(intent)
    }

    private fun getNotes(): List<Note> {
        val gson = Gson()
        val json = sharedPreferences.getString(KEY_EDITOR, KEY_EDITOR)
        val type: Type = object : TypeToken<List<Note?>?>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveNotes(list: List<Note>) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(KEY_EDITOR, json)
        editor.apply()
    }
}