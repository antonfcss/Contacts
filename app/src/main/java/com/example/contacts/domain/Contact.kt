package com.example.contacts.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val phoneContact: String,
    val imageContact: String
) : Parcelable
