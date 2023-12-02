package com.example.contacts.presentation.aboutContacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contacts.databinding.AboutContactFragmentBinding
import com.example.contacts.domain.Contact

class AboutContactsFragment : Fragment() {
    private lateinit var binding: AboutContactFragmentBinding


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
        val contactModel = arguments?.getParcelable<Contact>("contactModel") as Contact
        with(binding) {
            firstNameEditText.setText(contactModel.firstName)
            lastNameEditText.setText(contactModel.lastName)
            phoneEditText.setText(contactModel.phoneContact)
            saveButton.setOnClickListener {
                val firstName = binding.firstNameEditText.text.toString()
                val lastName = binding.lastNameEditText.text.toString()
                val phoneContact = binding.phoneEditText.text.toString()
                val result = Bundle().apply {
                    putString("firstName", firstName)
                    putString("lastName", lastName)
                    putString("phoneContact", phoneContact)
                }
                firstNameEditText.setText(firstName)
                lastNameEditText.setText(lastName)
                phoneEditText.setText(phoneContact)
                childFragmentManager.setFragmentResult("aboutContactsResult", result)
            }
        }
    }
}