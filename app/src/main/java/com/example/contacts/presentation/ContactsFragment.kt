package com.example.contacts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.contacts.R
import com.example.contacts.databinding.ContactsFragmentBinding
import com.example.contacts.domain.Contact
import com.example.contacts.presentation.aboutContacts.AboutContactsFragment
import com.example.contacts.presentation.adapter.ContactsAdapter
import com.example.contacts.presentation.adapter.ItemDecoration

class ContactsFragment : Fragment() {

    private lateinit var binding: ContactsFragmentBinding

    private val viewModel = ContactsViewModel.getInstance()

    private val contactsAdapter: ContactsAdapter by lazy {
        ContactsAdapter(
            { contactModel ->
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack("contactList")
                    .add(R.id.contactsFragment, AboutContactsFragment().apply {
                        arguments = bundleOf("contactModel" to contactModel)
                    })
                    .commit()
            },
            { contactModel ->
                showDeleteItem(contactModel)
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = contactsAdapter
        binding.recyclerView.addItemDecoration(
            ItemDecoration(100, requireContext())
        )
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.searchContacts(p0.orEmpty())
                return true
            }

        })
        viewModel.currentContacts.observe(viewLifecycleOwner) {
            contactsAdapter.setNewItems(it)
        }
        viewModel.getContactsList()
    }


    private fun showDeleteItem(contact: Contact) {
        AlertDialog.Builder(requireContext()).setTitle("Delete item")
            .setMessage("Do you want delete item?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.deleteContact(contact)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


}
