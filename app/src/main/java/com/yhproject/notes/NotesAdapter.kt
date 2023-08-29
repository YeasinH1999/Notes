package com.yhproject.notes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes : List<Note>, context: Context)
    : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {


    class NotesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView : TextView = itemView.findViewById(R.id.contentTextView)
        val editNote : CardView = itemView.findViewById(R.id.editNote)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        val note = notes[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        holder.editNote.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("notes_id", note.id)
            }
            holder.itemView.context.startActivity(intent)

        }

    }


    fun refreshData(newNotes : List<Note>) {

        notes = newNotes
        notifyDataSetChanged()

    }





}