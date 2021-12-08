package com.vivek.chat.room.messages;

import java.util.List;

/**
 * Difference in Room and Group is
 * - You can join a public Room by it's id
 * - You can join a group by an invite from admin
 * - You're joined in Room till you're connected
 * - You're part of group until you leave the group or removed by admin
 * - Room is more dynamic than a Group. Users can leave and join anytime. They see only the messages from the time they joined
 * - Group will persist conversations
 */
public class Room {

    private String roomId;
    private String roomName;
    private List<String> users;

}
