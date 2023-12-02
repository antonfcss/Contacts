package com.example.contacts.presentation

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts.data.ContactService
import com.example.contacts.domain.Contact

class ContactsViewModel : ViewModel() {

    private var _currentContacts: MutableLiveData<List<Contact>> = MutableLiveData<List<Contact>>()
    val currentContacts: LiveData<List<Contact>>
        get() = _currentContacts

    private var contactsData: MutableList<Contact> = mutableListOf()

    init {
        contactsData.addAll(ContactService().getContactsInitialList())
    }

    fun getContactsList() {
        _currentContacts.postValue(contactsData)
    }

    fun deleteContact(contact: Contact) {
        contactsData.remove(contact)
        _currentContacts.postValue(contactsData)
    }

    fun searchContacts(query: String) {
        _currentContacts.postValue(contactsData.filter { contact ->
            contact.firstName.contains(query, ignoreCase = true) ||
                    contact.lastName.contains(query, ignoreCase = true)
        })
    }

    fun changeContactData(contact: Contact) {
        contactsData = contactsData.map {
            if (it.id == contact.id) {
                contact
            } else {
                it
            }
        }.toMutableList()
        _currentContacts.postValue(contactsData)
    }

    companion object {
        private lateinit var instance: ContactsViewModel

        @MainThread
        fun getInstance(): ContactsViewModel {
            instance = if (::instance.isInitialized) instance else ContactsViewModel()
            return instance
        }
    }

}