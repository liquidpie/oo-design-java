# url-shortener

### Functional Requirements:

* Users need to be able to enter a long URL. Our service should save that URL and generate a short link
* Users should have the option to enter the expiration date. After that date passed, the short link should be invalid
* Clicking on the short link should redirect the user to the original long URL
* Users should create an account to use service. Service can have usage limit per user*
* User is allowed to create his own short link*
* Service should have metrics, for example, most visited links*

### Capacity Estimation
Our system will be read-heavy. There will be lots of redirection requests compared to new URL shortenings. Let’s assume a 100:1 ratio between read and write.

#### Traffic estimates
Assuming, we will have 500M new URL shortenings per month, with 100:1 read/write ratio, we can expect 50B redirections during the same period:
100 * 500M => 50B

What would be Queries Per Second (QPS) for our system? New URLs shortenings per second:
500 million / (30 days * 24 hours * 3600 seconds) = ~200 URLs/s

Considering 100:1 read/write ratio, URLs redirections per second will be:
100 * 200 URLs/s = 20K/s

#### Storage estimates
Let’s assume we store every URL shortening request (and associated shortened link) for 5 years. Since we expect to have 500M new URLs every month, the total number of objects we expect to store will be 30 billion:
500 million * 5 years * 12 months = 30 billion

Let’s assume that each stored object will be approximately 500 bytes (just a ballpark estimate–we will dig into it later). We will need 15TB of total storage:
30 billion * 500 bytes = 15 TB

#### Bandwidth estimates
For write requests, since we expect 200 new URLs every second, total incoming data for our service will be 100KB per second:
200 * 500 bytes = 100 KB/s

For read requests, since every second we expect ~20K URLs redirections, total outgoing data for our service would be 10MB per second:
20K * 500 bytes = ~10 MB/s

#### Memory estimates
If we want to cache some of the hot URLs that are frequently accessed, how much memory will we need to store them? If we follow the 80-20 rule, meaning 20% of URLs generate 80% of traffic, we would like to cache these 20% hot URLs.

Since we have 20K requests per second, we will be getting 1.7 billion requests per day:
20K * 3600 seconds * 24 hours = ~1.7 billion

To cache 20% of these requests, we will need 170GB of memory.
0.2 * 1.7 billion * 500 bytes = ~170GB

#### Throttling
How do we detect and prevent abuse? A malicious user can put us out of business by consuming all URL keys in the current design. To prevent abuse, we can limit users via their api_dev_key. Each api_dev_key can be limited to a certain number of URL creations and redirections per some time period

### Database Schema
We would need two tables: one for storing information about the URL mappings and one for the user’s data who created the short link.

**URL Table**
Hash: varchar(16) PK
OriginalURL: varchar(512)
CreationDate: datetime
ExpiratiomDate: datetime
UserID: int

**User Table**
UserID: int PK
Name: varchar(20)
Email: varchar(32)
CreationDate: datetime
LastLogin: datetime

### Shortening Algorithm

1. Hashing
2. UUID
3. Base62 conversion of auto_incrementing id from DB
4. Pre-computed unique urls

#### Hashing
Pass the long URL to a hashing function to obtain a fixed-length string (For eg:- 32-byte string). Then extract the first 6 characters to get the short URL.

However, there are two downsides of using the above approach:-

* Two different URLs can map to the same short URL. This is as a result of collisions. Using a uniform hash function can reduce the chances of a collision. Additionally, a pseudo-random number can be used for hashing
* If a User tries to shorten the same long URL multiple times, he should get a different result every time. In this case, due to collision, chances that the long URL returns the same short URL are high
* If multiple users enter the same URL, they can get the same shortened URL, which is not acceptable. 
* What if parts of the URL are URL-encoded? e.g., http://www.educative.io/distributed.php?id=design, and http://www.educative.io/distributed.php%3Fid%3Ddesign are identical except for the URL encoding.


#### Pre-computed unique urls
We can pre-generate a set of short URLs and persist it in the database. Since, the last two methods do not depend on the long url. It's fine to pre-compute short urls.
We can have a standalone Key Generation Service (KGS) that generates random six-letter strings beforehand and stores them in a database (let’s call it key-DB). Whenever we want to shorten a URL, we will take one of the already-generated keys and use it. This approach will make things quite simple and fast. Not only are we not encoding the URL, but we won’t have to worry about duplications or collisions. KGS will make sure all the keys inserted into key-DB are unique

The URL Shortener service will get a new short URL from this service. Subsequently, it will store the mapping between long & short URLs.

**Concurrency Issues**
As soon as a key is used, it should be marked in the database to ensure that it is not used again. If there are multiple servers reading keys concurrently, we might get a scenario where two or more servers try to read the same key from the database. How can we solve this concurrency problem?

Servers can use KGS to read/mark keys in the database. KGS can use two tables to store keys: one for keys that are not used yet, and one for all the used keys. As soon as KGS gives keys to one of the servers, it can move them to the used keys table. KGS can always keep some keys in memory to quickly provide them whenever a server needs them.

For simplicity, as soon as KGS loads some keys in memory, it can move them to the used keys table. This ensures each server gets unique keys. If KGS dies before assigning all the loaded keys to some server, we will be wasting those keys–which could be acceptable, given the huge number of keys we have.

Scaling the above system may result in concurrency issues. If not handled correctly, the same URL may get allocated to two different services.
Scaling the URL Shortener service

To solve this, we can associate state to a tiny URL. The tiny URL can have two states — ACTIVE, and INACTIVE. By default, all URLs will be in an INACTIVE state. Once it’s allocated to a client, it must be marked as ACTIVE. Only ACTIVE URLs can be assigned to clients.

To improve latency, the URL Generator Service can prefetch all tiny URLs from the datastore. This will avoid database calls for every new request. Moreover, it can now use a locking data structure to synchronize access by multiple client applications.

### How would we perform a key lookup? 
We can look up the key in our database to get the full URL. If it’s present in the DB, issue an “HTTP 302 Redirect” status back to the browser, passing the stored URL in the “Location” field of the request. If that key is not present in our system, issue an “HTTP 404 Not Found” status or redirect the user back to the homepage.

# add image here

### URL Conversion
The most important thing in a URL shortener is the conversion algorithm. URL conversion can be implemented in several different ways, and each way has its pros and cons. 

One way of generating short links would be hashing the original URL with some hash function (for example MD5 or SHA-2). When using a hash function, it is sure that different inputs will result in different outputs. The result of the hash is longer than seven characters, so we would need to take the first seven characters. But, in this case, there could be a collision because the first seven characters could already be in use as a short link. Then, we take the next seven characters, until we find a short link that is not used.

The second way of generating a short link is by using UUIDs. The probability that a UUID will be duplicated is not zero, but it is close enough to zero to be negligible. Since a UUID has 36 characters, that means that we have the same problem as above. We should take the first seven characters and check if that combination is already in use.

The third way would be converting numbers from base 10 to base 62. A base is a number of digits or characters that can be used to represent a particular number. Base 10 are digits [0-9], which we use in everyday life and base 62 are [0-9][a-z][A-Z]. This means that, for example, a number in base 10 with four digits would be the same number in base 62 but with two characters.

Using base 62 in URL conversion with a maximum length of seven characters allows us to have 62^7 unique values for short links.

How Base 62 Conversion Works

We have a base 10 number that we want to convert base 62. We are going to use the following algorithm:

```java
while(number > 0) {
    remainder = number % 62
    number = number / 62
}
```

### Implementation

We are going to use our database's auto-increment feature. The auto-incrementing number is going to be used for base 62 conversion. You can use any other database that has an auto-increment feature.

Notice that there is no short link attribute. We won't save short links. We are going to convert the id attribute from base 10 to base 62 every time there is a GET request. This way, we are saving space in our database.


####

**Basic solution:**

To make things easier, we can assume the alias is something like http://tinyurl.com/<alias_hash> and alias_hash is a fixed length string.
To begin with, let’s store all the mappings in a single database. A straightforward approach is using alias_hash as the ID of each mapping, which can be generated as a random string of length 7.

Therefore, we can first just store <ID, URL>. When a user inputs a long URL “http://www.google.com”, the system creates a random 7-character string like “abcd123” as ID and inserts entry <“abcd123”, “http://www.google.com”> into the database.

In the run time, when someone visits http://tinyurl.com/abcd123, we look up by ID “abcd123” and redirect to the corresponding URL “http://www.google.com”.

**Problem with this solution:**

We can't generate unique hash values for the given long URL. In hashing, there may be collisions (2 long urls map to same short url) and we need a unique short url for every long url so that we can access long url back but hash is one way function.

**Better Solution:**

One of the most simple but also effective one, is to have a database table set up this way:

```
Table Tiny_Url(
ID : int PRIMARY_KEY AUTO_INC,
Original_url : varchar,
Short_url : varchar
)
```

Then the auto-incremental primary key ID is used to do the conversion: (ID, 10) <==> (short_url, BASE). Whenever you insert a new original_url, the query can return the new inserted ID, and use it to derive the short_url, save this short_url and send it to cilent.

**Multiple machines:**

If we are dealing with massive data of our service, distributed storage can increase our capacity. The idea is simple, get a hash code from original URL and go to corresponding machine then use the same process as a single machine. For routing to the correct node in cluster, Consistent Hashing is commonly used.

Following is the pseudo code for example,

**Get shortened URL**

    - hash original URL string to 2 digits as hashed value hash_val
    - use hash_val to locate machine on the ring
    - insert original URL into the database and use getShortURL function to get shortened URL short_url
    - Combine hash_val and short_url as our final_short_url (length=8) and return to the user

**Retrieve original from short URL**

    - get first two chars in final_short_url as hash_val
    - use hash_val to locate the machine
    - find the row in the table by rest of 6 chars in final_short_url as short_url
    - return original_url to the user

**Other factors:**

One thing I’d like to further discuss here is that by using GUID (Globally Unique Identifier) as the entry ID, what would be pros/cons versus incremental ID in this problem?

If you dig into the insert/query process, you will notice that using random string as IDs may sacrifice performance a little bit. More specifically, when you already have millions of records, insertion can be costly. Since IDs are not sequential, so every time a new record is inserted, the database needs to go look at the correct page for this ID. However, when using incremental IDs, insertion can be much easier – just go to the last page.

**Note**

One thing to note is that when you set up a db for auto increment and if you make it distributed - then if you have to add a node or remove a node and rebalance the data, there will ID collision since each node has auto increment.

db1-123, 456, 789
db2-123, 456, 789
Even though content is differnet (original and short url), all ids in nodes will be same.
You're doing correct routing but ID has to be unique. If you want to rebalance data, add or delete node, you get id collisions.

To fix this - you can use two (or more) db just to create ids - ID_db1 creates odd id, ID_db2 creates even number ids. (basically just `return id if (id % (1 for ID_db1 or 2 for ID_db2) == 0) else auto_increment`). So even if one db goes down, we still proceed and fix and resume the process.

And now this id can be used in whatever nodes you wish by directly apply consistent hash on this id. no collision problem.

## Scale

### How to scale reads

**Caching**
By introducing a cache, we can scale the read queries. If multiple queries are received with the same short URL, the service can return long URL from the cache. We can use LRU (Least Recently Used) as the Cache eviction policy.

**Data replication**
We can segregate the reads from the write queries. Writes can be performed on the master and data can be replicated to slaves. Slaves can be used for executing read queries.

### How to shard the data

We have the following three strategies for sharding the data:-

**Range-based partitioning** 

In this strategy, URLs starting with ‘a-h’ can be assigned to server 1, the ones starting with ‘i-o’ to server 2 and so on. With this approach, there is a possibility of uneven data distribution. This might result in one shard having twice or thrice the data of another
    
**Hash-based partitioning** 

This method eliminates the possibility of uneven data distribution. However, it doesn’t scale well when new servers are introduced or existing ones are removed.

**Consistent Hashing** 

This partitioning strategy overcomes the limitations of the above two strategies. The data distribution is even and the addition or removal of new servers has an impact on a minimum number of records.


## Load Balancer
We can add a Load balancing layer at three places in our system:

    Between Clients and Application servers
    Between Application Servers and database servers
    Between Application Servers and Cache servers

Initially, we could use a simple Round Robin approach that distributes incoming requests equally among backend servers. This LB is simple to implement and does not introduce any overhead. Another benefit of this approach is that if a server is dead, LB will take it out of the rotation and will stop sending any traffic to it.

A problem with Round Robin LB is that we don’t take the server load into consideration. If a server is overloaded or slow, the LB will not stop sending new requests to that server. To handle this, a more intelligent LB solution can be placed that periodically queries the backend server about its load and adjusts traffic based on that.

### Cleanup of URLs

An asynchronous job needs to be scheduled to remove all the expired shortened URLs. This job will filter all the expired URLs and return them to the data store for future use.

If we chose to actively search for expired links to remove them, it would put a lot of pressure on our database. Instead, we can slowly remove expired links and do a lazy cleanup. Our service will ensure that only expired links will be deleted, although some expired links can live longer but will never be returned to users.

    Whenever a user tries to access an expired link, we can delete the link and return an error to the user.
    A separate Cleanup service can run periodically to remove expired links from our storage and cache. This service should be very lightweight and can be scheduled to run only when the user traffic is expected to be low.
    We can have a default expiration time for each link (e.g., two years).
    After removing an expired link, we can put the key back in the key-DB to be reused.
    Should we remove links that haven’t been visited in some length of time, say six months? This could be tricky. Since storage is getting cheap, we can decide to keep links forever.

### Security and Permissions

Can users create private URLs or allow a particular set of users to access a URL?

We can store the permission level (public/private) with each URL in the database. We can also create a separate table to store UserIDs that have permission to see a specific URL. If a user does not have permission and tries to access a URL, we can send an error (HTTP 401) back. Given that we are storing our data in a NoSQL wide-column database like Cassandra, the key for the table storing permissions would be the ‘Hash’ (or the KGS generated ‘key’). The columns will store the UserIDs of those users that have permission to see the URL.

### References

https://www.educative.io/courses/grokking-the-system-design-interview/m2ygV4E81AR