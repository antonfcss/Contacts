package com.example.contacts.presentation.adapter

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contacts.R
import com.example.contacts.databinding.RecyclerviewItemContactsBinding
import com.example.contacts.domain.Contact


class ContactsViewHolder(private val binding: RecyclerviewItemContactsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        contact: Contact,
        clickListener: (Contact) -> Unit,
        longClickListener: (Contact) -> Unit
    ) {
        with(binding) {
            if (contact.imageContact.isNotBlank()) {
                Glide.with(avatarImageView)
                    .load(contact.imageContact)
                    .circleCrop()
                    .placeholder(R.drawable.baseline_person_24)
                    .error(R.drawable.baseline_person_24)
                    .into(avatarImageView)
            } else {
                avatarImageView.setImageResource(R.drawable.baseline_person_24)
            }
            fullNameTextView.text =
                root.context.resources.getString(
                    R.string.full_name,
                    contact.firstName,
                    contact.lastName
                )
            number.text = contact.phoneContact
            root.setOnClickListener {
                clickListener.invoke(contact)
            }
            root.setOnLongClickListener {
                longClickListener.invoke(contact)
                true
            }
            callImageView.setOnClickListener {
                Toast.makeText(
                    binding.root.context,
                    "Where is my money, Libovskii?",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

}