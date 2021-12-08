package com.vivek.chat.room.messages;

import java.util.List;

/**
 *
 Client ( Mobile app / Browser, etc ) which calls sendMessage(senderId, recepientId, messageContent, clientMessageId)
 Application server which interprets the API call and calls DB to do the following:
 Puts in the serverTimestamp
 Figures out the conversation to which the message should be appended based on the other participant
 Figures out if a recent message exists with the clientMessageId
 Store the message
 Database server which stores the message.



 user -> messages
 user -> conversations
 conversation -> messages


 */
public class Conversation {

    private String conversationId;
    private List<String> participants;
    private List<Message> snippet;
    private long lastUpdatedTimestamp;

}
