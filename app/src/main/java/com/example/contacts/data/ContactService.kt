package com.example.contacts.data

import com.example.contacts.domain.Contact
import com.github.javafaker.Faker

class ContactService {
    fun getContactsInitialList(): List<Contact> {
        val faker = Faker.instance()
        val generateContacts = (1..100).map {
            Contact(
                id = it.toLong(),
                firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                phoneContact = faker.phoneNumber().subscriberNumber(9),
                imageContact = IMAGES
            )
        }.toMutableList()
        return generateContacts
    }

    companion object {
        private const val IMAGES = "https://picsum.photos/200/200"
    }

}