package com.vivek.chat.room.messages;

public class Dumper2 {

    /*


ChatWindow

User user;
ChatManager room; //interface to access ChatRoom
readDeliveryReceipts  boolean;
LinkedHashMap<id-userid, AcknowledgeMessage>messages;
Message currentMessage;
displayMessage(Message message);//Adds an entry to logs
acknowledgeMessage (String messageId);
acceptAcknowledgement(String messageId);

Message → TextMessage , Video Message , Audio Message (inheritance)

-string id;
-DateTime timestamp;
-User user;
-string text;

AcknowledgeMessage (use decorator Pattern) //Store Message logs

-Message msg;
-Type (ServerAccepted , SentToClient);

ChatManager //An Adapter Class to interact with ChatRoom

ChatRoom room;
sendMessage(Message message);
register(ChatWindow window);
deregister(ChatWindow window);
acknowledgeMessage(String messageId);

ChatRoom (Chat Session)

List<ChatWindow> chatWindows;
BlockedQueue<Messages> messagesToSend;

//Listener Pattern
register(ChatWindow window);
deregister(ChatWindow window);

receivingMessage(Message); //will add it to the Queue and send acknowledgement
//Listener Pattern
sendMessage(Message);   //Will iterate all the chatwindows


     */
}
