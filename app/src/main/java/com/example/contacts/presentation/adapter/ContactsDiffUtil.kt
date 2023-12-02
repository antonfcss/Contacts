package com.example.contacts.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.contacts.domain.Contact

class ContactsDiffUtil(
    private val oldContacts: List<Contact>,
    private val newContacts: List<Contact>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldContacts.size

    override fun getNewListSize(): Int = newContacts.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldContacts[oldItemPosition].id == newContacts[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldContacts[oldItemPosition] == newContacts[newItemPosition]
    }

}
