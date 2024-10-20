package com.example.noteapp

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NoteRepository(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("NoteAppPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveNote(note: Note) {
        val notes = getNotes().toMutableList()
        notes.add(note)
        val jsonNotes = gson.toJson(notes)
        sharedPreferences.edit().putString("notes", jsonNotes).apply()
    }
    fun saveNotes(notes: List<Note>) {
        val json = gson.toJson(notes)
        sharedPreferences.edit().putString("notes", json).apply()
    }
    fun getNotes(): List<Note> {
        val jsonNotes = sharedPreferences.getString("notes", null)
        return if (jsonNotes != null) {
            val type = object : TypeToken<List<Note>>() {}.type
            gson.fromJson(jsonNotes, type)
        } else {
            emptyList()
        }
    }

    fun getNoteById(id: String): Note? {
        return getNotes().find { it.id == id }
    }
    fun deleteNote(note: Note) {
        val notes = getNotes().toMutableList()
        notes.remove(note)
        saveNotes(notes)
    }
}