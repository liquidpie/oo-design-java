# chat-room

### Usecases

* one to one messaging
* group based messages
* media sharing (photoes)
* notification upon message received

Flow:

- When a user is Online, server sends the message
- When a user is away, server sends the push notification
- When a user is offline, server keeps the message and pushes the notification/messages when the client app establishes connection

### Implementation


    observer design pattern for notification,
    using priority queue for main chat feed to show latest one to one or group messages (based on timestamp)
    creating interfaces for camera access control to take picture/select media from local (just define methods in interface)
    interface for whatsapp settings, searching contact to send message
    classes for User, Group, ChatFeed (feed you see once you open the app), ChatThread (between 2 users or group messages),
    Singleton class of main whatsapp system where you can use composition with ChatFeed, SettingController, CameraController etc.


Basically, one of the most common ways to build a messaging app is to have a chat server that acts as the core of the whole system. When a message comes, it won’t be sent to the receiver directly. Instead, it goes to the chat server and is stored there first. And then, based on the receiver’s status, the server may send the message immediately to him or send a push notification.

A more detailed flow works like this:

    User A wants to send message “Hello Gainlo” to user B. A first send the message to the chat server.
    The chat server receives the message and sends an acknowledgement back to A, meaning the message is received. Based on the product, the front end may display a single check mark in A’s UI.
    Case 1: if B is online and connected to the chat server, that’s great. The chat server just sends the message to B.
    Case 2: If B is not online, the chat server sends a push notification to B.
    B receives the message and sends back an acknowledgement to the chat server.
    The chat server notifies A that B received the message and updates with a double check mark in A’s UI.


**Real-time**

The whole system can be costly and inefficient once it’s scaled to certain level. So any way we can optimize the system in order to support a huge amount of concurrent requests?

There are many approaches. One obvious cost here is that when delivering messages to the receiver, the chat server might need to spawn an OS process/thread, initialize HTTP (maybe other protocol) request and close connection at the end. In fact, this happens to every message. Even if we do the other way around that the receiver keeps requesting the server to check if there’s any new message, it’s still costly.

One solution is to use HTTP persistent connection. In a nutshell, receivers can make an HTTP GET request over a persistent connection that doesn’t return until the chat server provides any data back. Each request will be re-established when it’s timed out or interrupt. This approach provides a lot of advantages in terms of response time, throughput and cost.


**Online notification**

Obviously, the most straightforward approach is that once a user is online, he sends a notification to all his friends. But how would you evaluate the cost of this?

When it’s at the peak time, we roughly need O(average number of friends * peak users) of requests, which can be a lot when there are millions of users. And this cost can be even more than the message cost itself. One idea to improve this is to reduce unnecessary requests. For instance, we can issue notification only when this user reloads a page or sends a message. In other words, we can limit the scope to only “very active users”. Or we won’t send notification until a user has been online for 5min. This solves the cases where a user shows online and immediately goes offline.

### References

- https://massivetechinterview.blogspot.com/2015/07/design-chat-server-hello-world.html
- https://www.geeksforgeeks.org/a-group-chat-application-in-java/
- https://dev.to/zeyu2001/build-a-chatroom-app-with-python-44fa
- 


Chat Session - Again composition. Basically it'll have a List of Chat Windows. Each chat window registers to a chat session. Chat session is responsible to notify all chat windows when a new message appears. (Observer pattern anyone?)

The server can do batching, so multiple statuses change are batched together in one push to the client. Saves phone's battery and also avoid communication overhead.
We can use an in-memory key value store such as Redis/Memcache to store online statuses. Since all data is in-memory, each query coming in would be efficient.

We maintain a simple mapping of user_id to status in Redis.
When a user gets online, query each user in the friend list and return the statuses back to the client (Query will be fast since it is in-memory).
When a user changes status, publish the status change to each of his/her online friends. The list of online friends can be sent via the payload or stored as a cache in the server if the list is too huge.

One thing worth considering is people typically chat with friends who are from the same geographic region, so you probably want to store users from the same geographic region in the same data center.

what happens when we send a message to an user who is offline. Is it possible to store this message in some distributed event bus, or everything will be stored locally on the phone in sql database or preffered settings and when user is online , there will be initiatiated a peer to peer connection between our mobile phone and the user and everything will be pushed

I would store the message in the server side database. When the receiving user comes online, I will query the database for any pending offline messages and deliver them to the user immediately


#### Function 1: Real-time presence notification:


The most resource-intensive operation performed in a chat system is not sending messages.

It is rather keeping each online user aware of the online-idle-offline states of their friends,

so that conversations can begin.

The naive implementation of sending a notification to all friends whenever a user comes

online or goes offline has a worst case cost of O(average friendlist size * peak users * churn rate)

messages/second, where churn rate is the frequency with which users come online and go offline,

in events/second. This is wildly inefficient to the point of being untenable, given that the average

number of friends per user is measured in the hundreds, and the number of concurrent users

during peak site usage is on the order of several millions.


Surfacing connected users' idleness greatly enhances the chat user experience but further

compounds the problem of keeping presence information up-to-date. Each Facebook Chat user

now needs to be notified whenever one of his/her friends

(a) takes an action such as sending a chat message or loads a Facebook page

(if tracking idleness via a last-active timestamp) or

(b) transitions between idleness states (if representing idleness as a state machine with states like

"idle-for-1-minute", "idle-for-2-minutes", "idle-for-5-minutes", "idle-for-10-minutes", etc.).

Note that approach (a) changes the sending a chat message / loading a Facebook page

from a one-to-one communication into a multicast to all online friends, while approach (b)

ensures that users who are neither chatting nor browsing Facebook are nonetheless generating

server load.