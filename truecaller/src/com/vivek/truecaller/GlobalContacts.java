package com.vivek.truecaller;

import com.vivek.truecaller.model.tries.ContactTrie;

public class GlobalContacts {

    private GlobalContacts() {
    }

    public static GlobalContacts INSTANCE = new GlobalContacts();
    private ContactTrie contactTrie = new ContactTrie();

    public ContactTrie getContactTrie() {
        return contactTrie;
    }
}
