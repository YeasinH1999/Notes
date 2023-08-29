package com.yhproject.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yhproject.notes.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db : NotesDatabaseHelper
    private var notesId : Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        notesId = intent.getIntExtra("notes_id", -1)
        if (notesId == -1){
            finish()
            return
        }

        val notes = db.getNotesById(notesId)
        binding.updateTitleEditText.setText(notes.title)
        binding.updateContentEditText.setText(notes.content)

        binding.updateSaveButton.setOnClickListener {

            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updatedNote = Note(notesId, newTitle, newContent)
            db.updateNote(updatedNote)
            finish()

            Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()

        }

    }
}