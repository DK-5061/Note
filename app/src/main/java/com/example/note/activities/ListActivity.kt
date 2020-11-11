package com.example.note.activities


import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.note.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val KEY_EDITOR = "notes"
        const val KEY_SHARED_PREFERENCES = "prefID"
    }

    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        sharedPreferences = getSharedPreferences(KEY_SHARED_PREFERENCES, MODE_PRIVATE)

        val notes = getNotes()

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes ?: emptyList())

        val list: ListView = findViewById(R.id.listView1)
        list.adapter = adapter
    }

    private fun getNotes(): List<String?>? {
        val gson = Gson()
        val json = sharedPreferences.getString(KEY_EDITOR, KEY_EDITOR)
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return gson.fromJson(json, type)
    }

}