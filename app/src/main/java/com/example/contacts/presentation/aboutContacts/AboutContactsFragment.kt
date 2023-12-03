package com.example.contacts.presentation.aboutContacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contacts.databinding.AboutContactFragmentBinding
import com.example.contacts.presentation.ContactsViewModel

class AboutContactsFragment : Fragment() {
    private lateinit var binding: AboutContactFragmentBinding

    private val viewModel = ContactsViewModel.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AboutContactFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedContact.observe(viewLifecycleOwner) { selectedContact ->
            if (selectedContact != null) {
                with(binding) {
                    firstNameEditText.setText(selectedContact.firstName)
                    lastNameEditText.setText(selectedContact.lastName)
                    phoneEditText.setText(selectedContact.phoneContact)
                    saveButton.setOnClickListener {
                        viewModel.changeContactData(
                            selectedContact.copy(
                                firstName = firstNameEditText.text.toString(),
                                lastName = lastNameEditText.text.toString(),
                                phoneContact = phoneEditText.text.toString()
                            )
                        )
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                }
            }
        }
    }
}