package com.example.makemynotes.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView

@BindingAdapter("noteColor")
fun MaterialCardView.setNoteColor(color: Int) {
    setCardBackgroundColor(context.getColor(color))
}