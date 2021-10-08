# logger

### Google LLD round question

We need to implement an interface: a LogClient. This client has three methods: `start`, `end` and `poll`.

The start and end method are event listeners. They are notified of a process start and end.

The `poll` method waits on the first task in queue to complete, and then prints it.