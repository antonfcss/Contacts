package com.example.contacts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
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

    private val contactsAdapter: ContactsAdapter by lazy {
        ContactsAdapter(
            { contactModel ->
                val aboutContactsFragment = AboutContactsFragment().apply {
                    arguments = bundleOf("contactModel" to contactModel)
                }
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(
                        R.id.navigationFragmentContainer,
                        aboutContactsFragment,
                        null
                    )
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
    ): View? {
        binding = ContactsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val initList = arguments?.getParcelableArrayList<Contact>(CONTACT_TAG) as List<Contact>
        binding.recyclerView.adapter = contactsAdapter
        contactsAdapter.setNewItems(initList)
        binding.recyclerView.addItemDecoration(
            ItemDecoration(60, requireContext())
        )
        childFragmentManager.setFragmentResultListener(
            "aboutContactsResult",
            viewLifecycleOwner
        ) { _, result ->
            val contact = result.getParcelable<Contact>("aboutContactsResult")
            val newList =
                (arguments?.getParcelableArrayList<Contact>(CONTACT_TAG) as List<Contact>).map {
                    if (contact?.id == it.id) {
                        contact
                    } else {
                        it
                    }
                }
            contactsAdapter.setNewItems(newList)
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                filterList(p0)
                return true
            }

        })
    }

    /**Нужно помощь Сани с поиском, работает, но если мы ищем имя, находим его а потом очищаем список,
    то у нас показывается новый список с тем именем которое мы искали. Наверное это потому
    что я не сохраняю нигде список изначальный */
    private fun filterList(query: String?) {
        val filteredList = if (query.isNullOrBlank()) {
            arguments?.getParcelableArrayList<Contact>(CONTACT_TAG) as List<Contact>
        } else {
            (arguments?.getParcelableArrayList<Contact>(CONTACT_TAG) as List<Contact>).filter {
                it.firstName.contains(query, ignoreCase = true) || it.lastName.contains(
                    query,
                    ignoreCase = true
                )
            }
        }
        contactsAdapter.setNewItems(filteredList)
    }

    private fun showDeleteItem(contact: Contact) {
        AlertDialog.Builder(requireContext()).setTitle("Delete item")
            .setMessage("Do you want delete item?")
            .setPositiveButton("Yes") { _, _ ->

            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    companion object {
        private const val CONTACT_TAG = "Contact"
        fun newInstance(contacts: List<Contact>) = ContactsFragment().apply {
            arguments = bundleOf(CONTACT_TAG to contacts)
        }
    }
}
