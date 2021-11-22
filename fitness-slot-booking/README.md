# fitness-slot-booking

Fitness slot booking application

### Problem Statement:
We want to build a fitness training platform to allow a user to choose & book a class.
Classes can be of multiple types - yoga, gym, dance.
Each class has a capacity. This refers to the maximum number of users that can attend the class.
Users can book a class if the capacity is not yet reached.
If a class is already at capacity, the system should maintain a waiting list of interested users.
Users can cancel the slot upto 30 mins before the class starts. When a user cancels their booking, allocate the cancelled slot to the first user from the waitlist.

### Assume that:
APIs to register Users and classes are not needed, however models need to be present.
Authentication and authorization is not necessary. You can accept a username and assume that it is valid.

### Demonstrate:
Seed data of users and classes. At least 3 users and 3 classes are expected (static data is acceptable)
Book a slot in the list of classes available
If the size limit is not reached, booking is done
If the size limit is reached, put the user in the waiting list
User should be able to cancel a class within 30 mins before the class starts
When a user cancels the class, allocate it to the first person in the waiting list if not empty

### Evaluation Criteria:
Demonstration, Modelling and Extensibility of your Code.
Clean Professional Code.
A demonstration of the functional requirements stated above.
For the demonstration, you can choose any of - CLI, API, Unit tests or even main method.

### Additional Notes:
You may use any programming language.
Database is optional. We don’t recommend setting up a db, it takes a lot of time. In memory stores are acceptable for storing data.
You may access documentation and any other resources as needed.
