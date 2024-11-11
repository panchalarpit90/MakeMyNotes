package com.example.makemynotes.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.makemynotes.database.Notes
import com.example.makemynotes.databinding.NoteLayoutBinding

class NotesAdapter(private val onClickListener: (Notes) -> Unit) :
    ListAdapter<Notes, NotesAdapter.NotesViewHolder>(DiffUtilCallback()) {

    class NotesViewHolder(private val binding: NoteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Notes) {
            binding.item = note
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NoteLayoutBinding.inflate(layoutInflater, parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener(note)
        }
        holder.bind(note)
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Notes>() {
        override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
            return oldItem == newItem
        }
    }
}
