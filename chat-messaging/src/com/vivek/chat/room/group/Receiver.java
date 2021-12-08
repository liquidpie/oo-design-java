package com.vivek.chat.room.group;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.StandardCharsets;

public class Receiver {

    private static final int MAX_LEN = 1000;

    private final MulticastSocket socket;
    private final InetAddress group;
    private final int port;

    public Receiver(MulticastSocket socket, InetAddress group, int port) {
        this.socket = socket;
        this.group = group;
        this.port = port;
    }

    public void receive() {
        while (!GroupChat.finished) {
            byte[] buffer = new byte[MAX_LEN];
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
            String message;
            try {
                socket.receive(datagram);
                message = new String(buffer, 0, datagram.getLength(), StandardCharsets.UTF_8);
                if (!message.startsWith(GroupChat.name))
                    System.out.println(message);
            } catch (IOException e) {
                System.out.println("Socket Closed");
            }
        }
    }

}
