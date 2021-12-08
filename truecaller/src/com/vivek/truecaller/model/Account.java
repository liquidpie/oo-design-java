package com.vivek.truecaller.model;

import com.vivek.truecaller.exception.BlockLimitExceededException;
import com.vivek.truecaller.exception.ContactDoesNotExistsException;
import com.vivek.truecaller.exception.ContactsExceededException;
import com.vivek.truecaller.model.common.Contact;
import com.vivek.truecaller.model.common.PersonalInfo;
import com.vivek.truecaller.model.common.SocialInfo;
import com.vivek.truecaller.model.common.Tag;
import com.vivek.truecaller.model.tries.ContactTrie;
import orestes.bloomfilter.CountingBloomFilter;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Account {
    private String id;
    private String phoneNumber;
    private String userName;
    private String password;
    private LocalDateTime lastAccessed;
    private Tag tag;
    private Contact contact;
    private PersonalInfo personalInfo;
    private SocialInfo socialInfo;
    private Business business;
    private UserCategory userCategory;
    private Map<String, User> contacts;
    private CountingBloomFilter<String> blockedContacts;
    private Set<String> blockedSet;
    private ContactTrie contactTrie;

    public Account() {
    }

    public Account(String phoneNumber, String firstName) {
        this.phoneNumber = phoneNumber;
        this.personalInfo = new PersonalInfo(firstName);
    }

    public Account(String phoneNumber, String firstName, String lastName) {
        this(phoneNumber,firstName);
        this.personalInfo.setLastName(lastName);
    }

    public abstract void register(UserCategory userCategory, String userName, String password,
                                  String email, String phoneNumber, String countryCode,
                                  String firstName);
    public abstract void addConcat(User user) throws ContactsExceededException;
    public abstract void removeContact(String number) throws ContactDoesNotExistsException;
    public abstract void blockNumber(String number) throws BlockLimitExceededException;
    public abstract void unblockNumber(String number);
    public abstract void reportSpam(String number, String reason);
    public abstract void upgrade(UserCategory userCategory);
    public abstract boolean isBlocked(String number);
    public abstract boolean canReceive(String number);

    public abstract boolean importContacts(List<User> users);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public SocialInfo getSocialInfo() {
        return socialInfo;
    }

    public void setSocialInfo(SocialInfo socialInfo) {
        this.socialInfo = socialInfo;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public UserCategory getUserCategory() {
        return userCategory;
    }

    public void setUserCategory(UserCategory userCategory) {
        this.userCategory = userCategory;
    }

    public Map<String, User> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, User> contacts) {
        this.contacts = contacts;
    }

    public CountingBloomFilter<String> getBlockedContacts() {
        return blockedContacts;
    }

    public void setBlockedContacts(CountingBloomFilter<String> blockedContacts) {
        this.blockedContacts = blockedContacts;
    }

    public Set<String> getBlockedSet() {
        return blockedSet;
    }

    public void setBlockedSet(Set<String> blockedSet) {
        this.blockedSet = blockedSet;
    }

    public ContactTrie getContactTrie() {
        return contactTrie;
    }

    public void setContactTrie(ContactTrie contactTrie) {
        this.contactTrie = contactTrie;
    }
}