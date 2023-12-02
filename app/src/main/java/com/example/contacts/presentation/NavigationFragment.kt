package com.example.contacts.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contacts.R
import com.example.contacts.data.ContactService
import com.example.contacts.databinding.NavigationFragmentBinding

class NavigationFragment : Fragment() {

    private lateinit var binding: NavigationFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        childFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(
                R.id.fragmentContainer,
                ContactsFragment.newInstance(ContactService().getContactsInitialList())
            )
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NavigationFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}