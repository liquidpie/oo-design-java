package com.vivek.chat.room.group;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  A group chat application using MulticastSocket (Java Platform SE 7) class is discussed.
 *  A MulticastSocket is a (UDP) DatagramSocket, with additional capabilities for joining “groups” of other multicast hosts on the internet.
 *
 *  Run the program using two command line arguments as specified.
 *  A multicast host is specified by a class D IP address and by a standard UDP port number.
 *  Class D IP addresses are in the range 224.0.0.0 to 239.255.255.255, inclusive.
 *  The address 224.0.0.0 is reserved and should not be used.
 *
 *  Use the multicast host IP address as 239.0.0.0 and the port number as 1234 (since the port numbers 0 through 1023 are reserved).
 *  Start all three terminals first before sending the message,
 *  otherwise messages which are sent before starting the terminal are lost (since there is no facility of buffer incorporated to store the messages.)
 *  We need two threads in this application. One for accepting the user input (using the java.util.Scanner class) and the other for reading the messages sent from other clients.
 *
 *  Additional Points:
 *
 * - You can incorporate network security feature by performing encryption before sending the message over the network.
 * - Primitive techniques such as Caesar cipher or advanced methods such as RSA can be used to perform encryption-decryption. You can try using Java’s RMI (Remote Method Invocation) to perform the same task.
 */
public class GroupChat {

    private static final String TERMINATE = "Exit";
    static String name;
    static volatile boolean finished = false;

    public static void main(String[] args) {
        if (args.length != 2)
            System.out.println("Two arguments required <multicast-host> <port-number>");

        try {
            InetAddress group = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            name = scanner.nextLine();
            MulticastSocket socket = new MulticastSocket(port);

            // Since, we're deploying this on localhost only, else for subnet set it as 1
            socket.setTimeToLive(0);
            socket.joinGroup(group);

            Receiver receiver = new Receiver(socket, group, port);
            ExecutorService executor = Executors.newSingleThreadExecutor();

            // Spawn a thread for reading messages
            executor.submit(receiver::receive);

            // Sent to the current group
            System.out.println("Start typing messages...\n");
            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase(TERMINATE)) {
                    finished = true;
                    socket.leaveGroup(group);
                    socket.close();
                    break;
                }
                message = "[ " + name + " ] " + message;
                byte[] buffer = message.getBytes();
                DatagramPacket datagram = new DatagramPacket(buffer, buffer.length, group, port);
                socket.send(datagram);
            }
        } catch (SocketException e) {
            System.out.println("Error creating socket");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading/writing from/to socket");
            e.printStackTrace();
        }
    }

}
