package com.example.note.activities

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.note.R


class NoteAdapter(
    context: Context,
    private val notes: List<Note>
) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return notes.size
    }

    override fun getItem(position: Int): Any {
        return notes[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, view: View?, vg: ViewGroup?): View? {
        val v = inflater.inflate(R.layout.activity_list_view, null)
        val holder = ViewHolder()
        holder.uTitle = v.findViewById<View>(R.id.titleID) as TextView
        holder.uContent = v.findViewById<View>(R.id.contentID) as TextView
        v.tag = holder
        holder.uTitle?.text = notes[position].title
        holder.uContent?.text = notes[position].content
        return v
    }

    internal class ViewHolder {
        var uTitle: TextView? = null
        var uContent: TextView? = null
    }
}