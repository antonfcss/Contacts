package com.example.contacts

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.presentation.ContactsFragment
import com.example.contacts.presentation.aboutContacts.AboutContactsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.contactsFragment, ContactsFragment())
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.contactsFragment, ContactsFragment())
                .commit()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.aboutContactsFragment, AboutContactsFragment())
                .commit()
        }

    }

}