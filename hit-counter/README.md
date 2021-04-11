# Hit Counter

Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (i.e. the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

**Example**

```java
HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);
```

**Follow-up1:** Due to latency, several hits arrive roughly at the same time and the order of timestamps is not guaranteed chronological.

**Follow-up2:** What if the number of hits per second could be very large? Does the design scale?

### Solution

If the data comes in order, the straight forward approach is to put the incoming hits with timestamps into a queue.
Upon the call of counter.getHits, dequeue all the expired hits (timestamp < current time - 300) and return the resulting queue size.

#### Follow-up1
What if the data comes in unordered and several hits carry the same timestamp.

Since the queue approach wouldn’t work without ordered data, this time go with arrays to store the hit count in each unit of time.

If we are tracking hits in the past 5 mins in seconds granularity which is 300 seconds, create 2 arrays of size 300.

```java
int[] hits = new int[300];
TimeStamp[] times = new TimeStamp[300]; // timestamp of the last counted hit
```

Given an incoming request, mod its timestamp by 300 to see where it locates in the hits array.

int idx = timestamp % 300; => hits[idx] keeps the hit count took place in this second

But, before we increase the hit count at idx by 1, the timestamp really belongs to the second that hits[idx] is tracking.
timestamp[i] stores the timestamp of the last counted hit.

* If timestamp[i] > timestamp, this hit should be discarded since it did not happened in the past 5 minute.
* If timestamp[i] == timestamp, then hits[i] increase by 1.
* If timestamp[i] < timestamp, reset hits[i] to 1 since what it stored was the hit count before the past 5 minutes.

Upon the call of counter.getHits, traverse hits to get a sum of all hit count with a `timestamp[i] > currentTime - 300`.

#### Follow-up2
Major issue on scaling the hit counter is concurrency calls. When two hits write to the array at the same time, possibly one of them gets lost. A write lock protects the system from losing hits but it slows down the process.

In order to quickly handle large number of requests, move the hit counter to a distributed system and have several machines counting together. Hash the userID to assign them to different hosts. Add load balancer on top to make sure requests gets allocated evenly.

On each individual machine, take the approach in follow-up1 to gather the counts.
Upon reading, sum up the count across all machines. For a read-heavy application, put a cache on top so that multiple read requests that take place in the same second does not incur unnecessary cross-node communications.