package com.vivek.mqs.db;

import com.vivek.mqs.db.schema.Publisher;
import com.vivek.mqs.db.schema.Queue;
import com.vivek.mqs.db.schema.Subscriber;
import com.vivek.mqs.db.schema.Subscription;

public class InMemoryDataStore {

    private static InMemoryDataStore INSTANCE = null;

    public final Collection<String, Queue> queueCollection = new SimpleCollection<>();
    public final Collection<String, Publisher> publisherCollection = new SimpleCollection<>();
    public final Collection<String, Subscriber> subscriberCollection = new SimpleCollection<>();
    public final Collection<String, Subscription> subscriptionCollection = new SimpleCollection<>();

    private InMemoryDataStore() { }

    public static InMemoryDataStore getInstance() {
        if (INSTANCE == null) {
            synchronized (InMemoryDataStore.class) {
                if (INSTANCE == null) {
                    INSTANCE = new InMemoryDataStore();
                }
            }
        }
        return INSTANCE;
    }

}
