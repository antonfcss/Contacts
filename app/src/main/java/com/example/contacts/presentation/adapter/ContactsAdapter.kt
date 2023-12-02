package com.example.contacts.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.databinding.RecyclerviewItemContactsBinding
import com.example.contacts.domain.Contact

class ContactsAdapter(
    private val clickListener: (Contact) -> Unit,
    private val longClickListener: (Contact) -> Unit
) :
    RecyclerView.Adapter<ContactsViewHolder>() {

    private val contactsItem = ArrayList<Contact>()

    fun setNewItems(newContactItems: List<Contact>) {
        val diffCallBack = ContactsDiffUtil(contactsItem, newContactItems)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        contactsItem.clear()
        contactsItem.addAll(newContactItems)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            RecyclerviewItemContactsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return contactsItem.size
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.bind(contactsItem[position], clickListener, longClickListener)
    }
}