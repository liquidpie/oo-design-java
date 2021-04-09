# url-shortener

**Functional Requirements:**

* Users need to be able to enter a long URL. Our service should save that URL and generate a short link
* Users should have the option to enter the expiration date. After that date passed, the short link should be invalid
* Clicking on the short link should redirect the user to the original long URL
* Users should create an account to use service. Service can have usage limit per user*
* User is allowed to create his own short link*
* Service should have metrics, for example, most visited links*

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

#### Pre-computed unique urls
We can pre-generate a set of short URLs and persist it in the database. Since, the last two methods do not depend on the long url. It's fine to pre-compute short urls.

The URL Shortener service will get a new short URL from this service. Subsequently, it will store the mapping between long & short URLs.

### Concurrency Issues
Scaling the above system may result in concurrency issues. If not handled correctly, the same URL may get allocated to two different services.
Scaling the URL Shortener service

To solve this, we can associate state to a tiny URL. The tiny URL can have two states — ACTIVE, and INACTIVE. By default, all URLs will be in an INACTIVE state. Once it’s allocated to a client, it must be marked as ACTIVE. Only ACTIVE URLs can be assigned to clients.

To improve latency, the URL Generator Service can prefetch all tiny URLs from the datastore. This will avoid database calls for every new request. Moreover, it can now use a locking data structure to synchronize access by multiple client applications.

### Cleanup of URLs

An asynchronous job needs to be scheduled to remove all the expired shortened URLs. This job will filter all the expired URLs and return them to the data store for future use.

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